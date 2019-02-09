@extends('layouts.app_printshop')

@section('content')


<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Aggiungi un nuovo tipo di carta</div>

                <div class="card-body">
                  @if ($errors->any())
                    <div class="alert alert-danger">
                      <ul>
                          @foreach ($errors->all() as $error)
                            <li>{{ $error }}</li>
                          @endforeach
                      </ul>
                    </div><br />
                  @endif
                    <form method="post" action="{{ route('papers.store') }}">
                        <div class="form-group">
                            @csrf
                            <label for="name">Formato Carta (es. A4):</label>
                            <input type="text" class="form-control" name="formato_carta"/>
                        </div>
                        <div class="form-group">
                            <label for="price">Descrizione:</label>
                            <input type="text" class="form-control" name="descrizione_carta"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Aggiungi</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
