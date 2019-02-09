@extends('layouts.app_printshop')

@section('content')

<div class="container">
        <div class="row justify-content-center">
            <div class="col-md-3">
                <div class="card">
                    <div class="card-header">Sei una Copisteria?<br>Lavora con noi!</div>

                    <div class="card-body">

                      <a href="{{ route('login/printshop')}}"><button type="button" class="btn btn-primary btn-lg">LOGIN</button></a>
                      <a href="{{ route('register/printshop')}}"><button type="button" class="btn btn-primary btn-lg">REGISTRA</button></a>

                </div>
            </div>
        </div>
    </div>
</div>

@endsection
