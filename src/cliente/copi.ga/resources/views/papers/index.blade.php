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
              <div class="card-header">Ecco i Tipi di Carta della tua Copisteria <a href="{{ route('papers.create')}}" class="btn btn-success float-right">Aggiungi Nuovo</a></div>
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
                              <td>Tipo Carta</td>
                              <td>Descrizione</td>
                              <td colspan="2">Azioni</td>
                            </tr>
                        </thead>
                        <tbody>
                            @foreach($papers as $paper)
                            <tr>
                                <td>{{$paper->formato}}</td>
                                <td>{{$paper->descrizione}}</td>
                                <td><a href="{{ route('papers.edit',$paper->id)}}" class="btn btn-primary">Modifica</a></td>
                                <td>
                                    <form action="{{ route('papers.destroy', $paper->id)}}" method="post">
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
