@extends('layouts.app_printshop')

@section('content')


<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Aggiungi un nuovo tipo di rilegatura</div>

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
                    <form method="post" action="{{ route('bookbindings.store') }}">
                        <div class="form-group">
                            @csrf
                            <label for="name">Tipo Rilegatura (es. Ad Anelli):</label>
                            <input type="text" class="form-control" name="tipo_rilegatura"/>
                        </div>
                        <div class="form-group">
                            <label for="price">Descrizione:</label>
                            <input type="text" class="form-control" name="descrizione_rilegatura"/>
                        </div>
                        <button type="submit" class="btn btn-primary">Aggiungi</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
