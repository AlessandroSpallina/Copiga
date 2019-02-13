<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Bookbinding;
use Illuminate\Support\Facades\Auth;

class BookbindingController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $bookbindings = Auth::user()->bookbindings()->get();

        return view('bookbindings.index', compact('bookbindings'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('bookbindings.create');
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
          'tipo_rilegatura' => 'required'
        ]);

        $bookbinding = new Bookbinding();
        $bookbinding->tipo = $request->get('tipo_rilegatura');
        $bookbinding->descrizione = $request->get('descrizione_rilegatura');
        Auth::user()->bookbindings()->save($bookbinding);

        return redirect('/printshop/bookbindings')->with('success', 'Creata la tipologia di rilegatura "'.$request->get('tipo_rilegatura').'" con successo.');
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
        $bookbinding = Bookbinding::find($id);

        return view('bookbindings.edit', compact('bookbinding'));
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
          'tipo_rilegatura' => 'required'
        ]);


        $bookbinding = Bookbinding::find($id);
        $bookbinding->tipo = $request->get('tipo_rilegatura');
        $bookbinding->descrizione = $request->get('descrizione_rilegatura');
        $bookbinding->save();

        return redirect('/printshop/bookbindings')->with('success', 'Aggiornata la tipologia di rilegatura "'.$request->get('tipo_rilegatura').'" con successo.');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $bookbinding = Bookbinding::find($id);
        $bookbinding->delete();

        return redirect('/printshop/bookbindings')->with('success', 'Tipo di rilegatura eliminato con successo.');
    }
}
