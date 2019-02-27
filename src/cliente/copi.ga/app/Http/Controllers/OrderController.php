<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Paper;
use App\Bookbinding;
use App\Credit;
use App\Printshop;
use App\Order;
use Illuminate\Support\Facades\Auth;

class OrderController extends Controller
{
    private function getPDFPages($document)
    {
        $cmd = "/usr/bin/pdfinfo";
        $filepath = '/var/www/html/Copiga/src/cliente/copi.ga/public/storage/'.$document;


        // Parse entire output
        // Surround with double quotes if file name has spaces
        exec("$cmd \"$filepath\"", $output);

        // Iterate through lines
        $pagecount = 0;
        foreach ($output as $op) {
            // Extract the number
            if (preg_match("/Pages:\s*(\d+)/i", $op, $matches) === 1) {
                $pagecount = intval($matches[1]);
                break;
            }
        }
        return $pagecount;
    }

    public function showPrintshopsByCredits()
    {
        $joined = Auth::user()->credits()
                  ->rightJoin('printshops', 'credits.printshop_id', '=', 'printshops.id')
                  ->select('credits.id', 'credits.disponibile', 'credits.printshop_id', 'printshops.name')
                  ->get();

        foreach ($joined as $j) {
            $ps = Printshop::find($j->printshop_id);
            $papers = $ps->papers()
                      ->where('selezionato', true)
                      ->select('id', 'formato', 'descrizione', 'prezzoBN', 'prezzoC')
                      ->get();
            $bookbindings = $ps->bookbindings()
                            ->where('selezionato', true)
                            ->select('id', 'tipo', 'descrizione', 'prezzo')
                            ->get();
            $j->papers = $papers;
            $j->bookbindings = $bookbindings;
        }

        $joined->transform(function ($i) {
            unset($i->printshop_id);
            return $i;
        });


        return view('order', [
          'credits' => $joined
        ]);
    }

    public function createOrder(Request $request)
    {
        if ($request->fileUpload->isValid()) {
            $file = request()->fileUpload;

            $order = new Order();
            $order->filename = $file->hashName();
            $order->filehash = hash_file('md5', $file);
            $order->filesize = $file->getClientSize();
            $order->paper_id = $request->get('paper');
            $order->bookbinding_id = $request->get('bookbinding');
            $order->bothSides = ($request->get('bothSides') == "yes") ? true : false;
            $order->color = ($request->get('color') == "yes") ? true : false;
            $order->pagesForSide = intval($request->get('pagesForSide'));

            $file->store('public');

            // --- calcolo del prezzo (PDF ONLY AL MOMENTO) ---
            $npag = $this->getPDFPages($order->filename);
            $paperTmp = Paper::find($order->paper_id);
            $paperPrice = ($order->color == true) ? $paperTmp->prezzoC : $paperTmp->prezzoBN;
            $bookbindingTmp = Bookbinding::find($order->bookbinding_id);
            $bookbindingPrice = $bookbindingTmp->prezzo;
            $order->price = (($npag * $paperPrice) + $bookbindingPrice);
            // ------------------------------------------------

            Auth::user()->credits()->find($request->get('selezione_copisteria'))->orders()->save($order);

            $confirmData = collect(['created_at' => $order->created_at, 'id' => $order->id, 'link' => asset('storage/'.$order->filename),  'filename' => $file->getClientOriginalName(), 'size' => ($order->filesize/1000), 'md5' => $order->filehash, 'paper' => $paperTmp->formato, 'bookbinding' => $bookbindingTmp->tipo, 'bothSides' => $order->bothSides, 'color' => $order->color, 'pagesForSide' => $order->pagesForSide, 'price' => $order->price]);

            return view('confirm_order', [
            'order' => $confirmData
          ]);
        }
    }

    public function confirmOrder(Request $request)
    {
      $action = $request->get('action');
      $order = Order::find($request->get('order'));

      if ($action == 'abort') {
        $order->delete();
        return redirect('/order-history')->with('success', 'L\'Ordine è stato cancellato.');
      } else if ($action == 'confirm') {
        $order->confirmed = true;
        $order->save();
        $credit = Auth::user()->credits()->find($order->credit_id);
        $credit->disponibile -= $order->price;
        $credit->save();
        return redirect('/order-history')->with('success', 'Ordine creato con successo, riceverai una e-mail quando la copisteria avrà stampato il tuo file.');
      }
      dd($request);

    }
}
