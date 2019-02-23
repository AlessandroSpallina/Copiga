@extends('layouts.app_printshop')

@section('content')

<style>
  .uper {
    margin-top: 40px;
  }
</style>

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
          <div class="card">
              <div class="card-header">Ecco i Tipi di File della tua Copisteria <a href="{{ route('files.create')}}" class="btn btn-success float-right">Aggiungi Nuovo</a></div>
              <div class="card-body">
                <div class="container">
                  <div class="table-wrapper">
                    <div class="uper">
                      @if(session()->get('success'))
                        <div class="alert alert-success">
                          {{ session()->get('success') }}
                        </div><br />
                      @endif
                      <table class="table table-bordered">
                        <thead>
                            <tr>
                              <td>Categoria File</td>
                              <td>Estensioni File</td>
                              <td colspan="2">Azioni</td>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach($files as $file)
                            <tr>
                                <td>{{$file->categoria}}</td>
                                <td>{{$file->estensione}}</td>
                                <td><a href="{{ route('files.edit',$file->id)}}" class="btn btn-primary">Modifica</a></td>
                                <td>
                                    <form action="{{ route('files.destroy', $file->id)}}" method="post">
                                      @csrf
                                      @method('DELETE')
                                      <button class="btn btn-danger" type="submit">Elimina</button>
                                    </form>
                                </td>
                            </tr>
                            @endforeach
                        </tbody>
                      </table>
                    <div>
                  </div>
                </div>

              </div>
          </div>
        </div>
    </div>
</div>





@endsection
