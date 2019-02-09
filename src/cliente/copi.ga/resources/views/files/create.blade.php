@extends('layouts.app_printshop')

@section('content')


<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Aggiungi un nuovo tipo di file</div>

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
                    <form method="post" action="{{ route('files.store') }}">
                        <div class="form-group">
                            @csrf
                            <label for="name">Categoria File (es. Immagine):</label>
                            <input type="text" class="form-control" name="categoria_file"/>
                        </div>
                        <div class="form-group">
                            <label for="price">Estensioni File: inserire tutte le estensioni da associare alla categoria definita sopra con il formato che segue: estensione;estensione;estensione (es. jpg;jpeg;png) :</label>
                            <input type="text" class="form-control" name="estensioni_file"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Aggiungi</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
