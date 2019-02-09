<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Paper;
use Illuminate\Support\Facades\Auth;

class PaperController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $papers = Auth::user()->papers()->get();

        return view('papers.index', compact('papers'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('papers.create');
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $request->validate([
          'formato_carta' => 'required'
        ]);

        $paper = new Paper();
        $paper->formato = $request->get('formato_carta');
        $paper->descrizione = $request->get('descrizione_carta');
        Auth::user()->papers()->save($paper);

        return redirect('/printshop/papers')->with('success', 'Creata la tipologia di carta "'.$request->get('formato_carta').'" con successo.');
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $paper = Paper::find($id);

        return view('papers.edit', compact('paper'));
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $request->validate([
          'formato_carta' => 'required'
        ]);


        $paper = Paper::find($id);
        $paper->formato = $request->get('formato_carta');
        $paper->descrizione = $request->get('descrizione_carta');
        $paper->save();

        return redirect('/printshop/papers')->with('success', 'Aggiornata la tipologia di carta "'.$request->get('formato_carta').'" con successo.');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $paper = Paper::find($id);
        $paper->delete();

        return redirect('/printshop/papers')->with('success', 'Tipo di carta eliminato con successo.');
    }
}
