<?php


// !!!! NOTA BENE: Questo è il controller delle API Autenticate !!!!

namespace App\Http\Controllers;

use Illuminate\Support\Facades\Auth;
use Illuminate\Database\Eloquent\ModelNotFoundException;
use App\Http\Controllers\Controller;
use App\Order;
use App\Credit;
use App\Printshop;
use App\User;
use App\Paper;
use App\Bookbinding;
use App\Mail\OrderMail;
use Illuminate\Support\Facades\Mail;

class AuthController extends Controller
{
    /**
     * Create a new AuthController instance.
     *
     * @return void
     */
    public function __construct()
    {
        auth()->setDefaultDriver('api');
        $this->middleware('auth:api', ['except' => ['login']]);
    }

    /**
     * Get a JWT via given credentials.
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function login()
    {
        $credentials = request(['email', 'password']);

        if (! $token = auth()->attempt($credentials)) {
            return response()->json(['error' => 'Unauthorized'], 401);
        }

        return $this->respondWithToken($token);
    }

    /**
     * Get the authenticated User.
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function me()
    {
        return response()->json(auth()->user());
    }

    /**
     * Log the user out (Invalidate the token).
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function logout()
    {
        auth()->logout();

        return response()->json(['message' => 'Successfully logged out']);
    }

    /**
     * Refresh a token.
     *
     * @return \Illuminate\Http\JsonResponse
     */
    public function refresh()
    {
        return $this->respondWithToken(auth()->refresh());
    }

    /**
     * Get the token array structure.
     *
     * @param  string $token
     *
     * @return \Illuminate\Http\JsonResponse
     */
    protected function respondWithToken($token)
    {
        return response()->json([
            'access_token' => $token,
            'token_type' => 'bearer',
            'expires_in' => auth()->factory()->getTTL() * 60
        ]);
    }

    public function diffjobs()
    {
        $credits_tmp = Credit::all()
                       ->where('printshop_id', auth()->user()->id);

        $credits = [];
        $users = [];

        foreach ($credits_tmp as $credit) {
          array_push($credits, $credit['id']);
          array_push($users, User::find($credit['user_id'])->email);
        }

        $orders_tmp = Order::all()
                        ->where('printed', false);
        $orders = [];
        $requestime = strtotime(request('time'));

        foreach ($orders_tmp as $order) {
          $i = array_search($order['credit_id'], $credits);
          if(($order['confirmed'] == true) && ($i !== false) && ($requestime <= strtotime($order['created_at']))) {
            array_push($orders, [
                                  'id' => (string) $order['id'],
                                  'time' => $order['created_at']->format('H:i d-m-Y'),
                                  'customer' => $users[$i],
                                  'paper' => Paper::find($order['paper_id'])->formato,
                                  'bookbinding' => Bookbinding::find($order['bookbinding_id'])->tipo,
                                  'filename' => $order['filename'],
                                  'filelink' => asset('storage/'.$order['filename']),
                                  'bothsides' => ($order['bothSides'] == true) ? 'yes' : 'no',
                                  'colour' => ($order['color'] == true) ? 'yes' : 'no',
                                  'pagesforside' => (string) $order['pagesForSide'],
                                  'price' => (string) $order['price'],
                                  'accepted' => ($order['accepted'] == true) ? 'yes' : 'no'
                                ]);
          }
        }
        return response()->json($orders);
    }

    public function accept()
    {
      $credits_tmp = Credit::all()
                      ->where('printshop_id', auth()->user()->id);

      $credits = [];

      foreach ($credits_tmp as $credit) {
        array_push($credits, $credit['id']);
      }

      $order = Order::find(request('id'));

      if((!$order) || (!in_array($order['credit_id'], $credits))) { //se non esiste l'id o non esiste l'id sotto il dominio di quella copisteria
        return response()->json(['error' => 'Not Found'], 404);
      }

      if($order['accepted']) {
        return response()->json(['status' => 'Job Already Accepted']);
      } else {
        $order['accepted'] = true;
        $order->save();


        $credit = Credit::find($order['credit_id']);
        $credit->totale -= $order->price;
        $credit->save();


        $user = User::find($credit->user_id);

        Mail::to($user['email'])->send(new OrderMail($user, 'Ordine Accettato', 'Il tuo ordine è stato <b>accettato</b> dalla copisteria.<br>Riceverai un\'altra email quando potrai andare a ritirare le tue stampe.', $order));

        return response()->json(['status' => 'ok']);
      }

    }

    public function print()
    {
      $credits_tmp = Credit::all()
                      ->where('printshop_id', auth()->user()->id);

      $credits = [];

      foreach ($credits_tmp as $credit) {
        array_push($credits, $credit['id']);
      }

      $order = Order::find(request('id'));

      if((!$order) || (!in_array($order['credit_id'], $credits))) { //se non esiste l'id o non esiste l'id sotto il dominio di quella copisteria
        return response()->json(['error' => 'Not Found'], 404);
      }

      if($order['printed']) {
        return response()->json(['status' => 'Job Already Printed']);
      } else {
        if($order['accepted']) {
          $order['printed'] = true;
          $order->save();

          $user_id = Credit::find($order['credit_id'])->user_id;
          $user = User::find($user_id);

          Mail::to($user['email'])->send(new OrderMail($user, 'Ordine Stampato', 'Il tuo ordine è stato <b>stampato</b>!<br>Puoi adesso recarti in copisteria e ritirare il tuo ordine.', $order));

          return response()->json(['status' => 'ok']);
        } else {
          return response()->json(['status' => 'Need to Accept the Job first']);
        }
      }

    }




}
