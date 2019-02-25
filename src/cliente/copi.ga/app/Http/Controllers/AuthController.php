<?php


// !!!! NOTA BENE: Questo è il controller delle API Autenticate !!!!

namespace App\Http\Controllers;

use Illuminate\Support\Facades\Auth;
use App\Http\Controllers\Controller;
use App\Order;
use App\Credit;
use App\Printshop;
use App\User;
use App\Paper;
use App\Bookbinding;

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
        $time = request('time');

        $credits_tmp = Credit::all()
                       ->where('printshop_id', auth()->user()->id);

        $credits = [];
        $users = [];
        $papers = [];
        $bookbindings = [];

        foreach ($credits_tmp as $credit) {
          array_push($credits, $credit['id']);
          array_push($users, User::find($credit['user_id'])->email);
        }

        $orders_tmp = Order::all();
        $orders = [];

        foreach ($orders_tmp as $order) {
          $i = array_search($order['credit_id'], $credits);

          if(($i !== false)) {
            array_push($orders, [
                                  'time' => $order['created_at']->format('H:i d-m-Y'),
                                  'customer' => $users[$i],
                                  'paper' => Paper::find($order['paper_id'])->formato,
                                  'bookbinding' => Bookbinding::find($order['bookbinding_id'])->tipo,
                                  'filename' => $order['filename'],
                                  'filelink' => asset('storage/'.$order['filename']),

                                ]);
          }
        }



        return response()->json($orders);

    }
}
