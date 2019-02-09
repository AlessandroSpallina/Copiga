<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\File;
use Illuminate\Support\Facades\Auth;

class FileController extends Controller
{
    private function extStringToArray($extstr)
    {
        $tmp_array = array_filter(explode(';', $extstr));
        return($tmp_array);
    }


    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        $files = Auth::user()->files()->get();

        return view('files.index', compact('files'));
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        return view('files.create');
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
        'categoria_file' => 'required',
        'estensioni_file' => 'required'
        ]);

        $count = count($this->extStringToArray($request->get('estensioni_file')));
        $file = new File();
        $file->categoria = $request->get('categoria_file');
        $file->estensione = $request->get('estensioni_file');
        Auth::user()->files()->save($file);

        return redirect('/printshop/files')->with('success', 'Creata la categoria "'.$request->get('categoria_file').'" con '.$count.' estensioni.');
    }



    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $file = File::find($id);

        return view('files.edit', compact('file'));
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
        'categoria_file' => 'required',
        'estensioni_file' => 'required'
        ]);

        $file = File::find($id);
        $file->categoria = $request->get('categoria_file');
        $file->estensione = $request->get('estensioni_file');
        $file->save();
        $count = count($this->extStringToArray($request->get('estensioni_file')));
        return redirect('/printshop/files')->with('success', 'Aggiornata la categoria "'.$request->get('categoria_file').'" con '.$count.' estensioni.');
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $file = File::find($id);
        $file->delete();

        return redirect('/printshop/files')->with('success', 'Categoria eliminata con successo.');
    }
}
