<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\File;
use App\Page;
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

        return view('printspecs', [
          'files' => $files,
          'papers' => $papers
        ]);
    }

    public function createPrintspecs(Request $request)
    {
        $files = Auth::user()->files()->get();
        $papers = Auth::user()->papers()->get();
        //dd(Auth::user()->files()->get()->find(1));

        $file_selezionati = $this->stringToArray($request->get('selezione_file'));

        $carta_selezionati = $this->stringToArray($request->get('selezione_carta'));
        $carta_bn = $this->stringToPrices($this->stringToArray($request->get('prezzoBN_carta')));
        $carta_c = $this->stringToPrices($this->stringToArray($request->get('prezzoC_carta')));




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
          if(in_array($paper->id, $carta_selezionati)) {
            $paper->selezionato = true;
            if($carta_bn[$file->id] > 0) {
              $paper->prezzoBN = $carta_bn[$file->id];
            }
            if($carta_c[$file->id] > 0) {
              $paper->prezzoC = $carta_c[$file->id];
            }
          } else {
            $paper->selezionato = false;
          }
          $paper->save();
        }



        return view('printspecs', [
          'files' => $files,
          'papers' => $papers
        ])->with('success', 'Specifiche di Stampa e Tariffario aggiornato con successo.');
    }
}
