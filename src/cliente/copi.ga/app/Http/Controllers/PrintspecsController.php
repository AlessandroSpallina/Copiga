<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\File;
use App\Page;
use App\Bookbinding;
use Illuminate\Support\Facades\Auth;

class PrintspecsController extends Controller
{
    private function stringToArray($stringa)
    {
        $tmp_array = array_filter(explode('|', $stringa));
        return($tmp_array);
    }


    /*
      input: 4:0.06|7:0.07| (stringa di esempio)
      in output: [4 => 0.06, 7 => 0.07]
    */
    private function stringToPrices($stringa)
    {
        $ret = [];
        foreach($stringa as $elem) {
          $aus = explode(':', $elem);
          $ret[$aus[0]] = floatval($aus[1]);
        }
        return $ret;
    }

    public function showPrintspecs()
    {
        $files = Auth::user()->files()->get();
        $papers = Auth::user()->papers()->get();
        $bookbindings = Auth::user()->bookbindings()->get();

        return view('printspecs', [
          'files' => $files,
          'papers' => $papers,
          'bookbindings' => $bookbindings
        ]);
    }

    public function createPrintspecs(Request $request)
    {
        $files = Auth::user()->files()->get();
        $papers = Auth::user()->papers()->get();
        $bookbindings = Auth::user()->bookbindings()->get();
        //dd(Auth::user()->files()->get()->find(1));

        $file_selezionati = $this->stringToArray($request->get('selezione_file'));

        $carta_selezionati = $this->stringToArray($request->get('selezione_carta'));
        $carta_bn = $this->stringToPrices($this->stringToArray($request->get('prezzoBN_carta')));
        $carta_c = $this->stringToPrices($this->stringToArray($request->get('prezzoC_carta')));

        $rilegatura_selezionati = $this->stringToArray($request->get('selezione_rilegatura'));
        $rilegatura_prezzo = $this->stringToPrices($this->stringToArray($request->get('prezzo_rilegatura')));

        //dd($rilegatura_prezzo);



        // @findme : questi foeach possono essere ottimizzati, viene fatta una save() per ogni tupla anche se con un if ti eviti la save

        foreach($files as $file) {
          if(in_array($file->id, $file_selezionati)) {
            $file->selezionato = true;
          } else {
            $file->selezionato = false;
          }
          $file->save();
        }

        foreach($papers as $paper) {
          if(in_array($paper->id, $carta_selezionati)) {  //se è selezionato
            if(array_key_exists($paper->id, $carta_bn) && (array_key_exists($paper->id, $carta_c))) {  //se è inserito un prezzo
              if(($carta_bn[$paper->id] > 0) && ($carta_c[$paper->id] > 0)) {  //se il prezzo inserito è maggiore di 0
                $paper->selezionato = true;
                $paper->prezzoBN = $carta_bn[$paper->id];
                $paper->prezzoC = $carta_c[$paper->id];
              } else {
                $paper->selezionato = false;
              }
            } else {
              $paper->selezionato = false;
            }
          } else {
            $paper->selezionato = false;
          }
          $paper->save();
        }

        foreach($bookbindings as $bookbinding) {
          if(in_array($bookbinding->id, $rilegatura_selezionati)) {  //se è selezionato
            if(array_key_exists($bookbinding->id, $rilegatura_prezzo)) {  //se è inserito un prezzo
              if($rilegatura_prezzo[$bookbinding->id] > 0) {  //se il prezzo inserito è maggiore di 0
                $bookbinding->selezionato = true;
                $bookbinding->prezzo = $rilegatura_prezzo[$bookbinding->id];
              } else {
                $bookbinding->selezionato = false;
              }
            } else {
              $bookbinding->selezionato = false;
            }
          } else {
            $bookbinding->selezionato = false;
          }
          $bookbinding->save();
        }



        return view('printspecs', [
          'files' => $files,
          'papers' => $papers,
          'bookbindings' => $bookbindings
        ])->with('success', 'Specifiche di Stampa e Tariffario aggiornato con successo.');
    }
}
