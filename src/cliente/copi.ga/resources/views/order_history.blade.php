@extends('layouts.app_user')

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
              <div class="card-header">Ecco lo Storico dei tuoi Ordini <a href="{{ route('order')}}" class="btn btn-success float-right">Crea Nuovo</a></div>
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
                              <td>Data</td>
                              <td>File</td>
                              <td>Copisteria</td>
                              <td>Prezzo</td>
                              <td>Stato</td>
                              <td colspan="2">Azioni</td>
                            </tr>
                        </thead>
                        <tbody>
                          @foreach($content as $c)
                          <tr>
                              <td>{{$c->created_at}}</td>
                              <td><a href="{{$c->filename}}" target="_blank" class="btn btn-outline-dark btn-sm">Link</a></td>
                              <td>{{$c->printshop_id}}</td>
                              <td>{{$c->price}} €</td>
                              <td>


                                @if ($c->accepted)
                                    @if ($c->printed)
                                      <span class="badge badge-success">Stampato</span>
                                    @else
                                      <span class="badge badge-warning">Accettato</span>
                                    @endif
                                @else
                                    <span class="badge badge-secondary">Inviato</span>
                                @endif
                              </td>


                              <td>{{$c->printshop_id}}</td>
                              <td><a href="" target="_blank" class="btn btn-outline-primary btn-sm">Dettagli</a></td>
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
