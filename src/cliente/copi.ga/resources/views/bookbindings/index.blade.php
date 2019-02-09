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
              <div class="card-header">Ecco i Tipi di Rilegatura della tua Copisteria <a href="{{ route('bookbindings.create')}}" class="btn btn-success float-right">Aggiungi Nuovo</a></div>
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
                              <td>Tipo Rilegatura</td>
                              <td>Descrizione</td>
                              <td colspan="2">Azioni</td>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach($bookbindings as $bookbinding)
                            <tr>
                                <td>{{$bookbinding->tipo}}</td>
                                <td>{{$bookbinding->descrizione}}</td>
                                <td><a href="{{ route('bookbindings.edit',$bookbinding->id)}}" class="btn btn-primary">Modifica</a></td>
                                <td>
                                    <form action="{{ route('bookbindings.destroy', $bookbinding->id)}}" method="post">
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
