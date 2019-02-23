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
