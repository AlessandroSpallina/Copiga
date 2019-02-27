@extends('layouts.app_auth')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card">
                <div class="card-header">Verifica il tuo Indirizzo Email</div>

                <div class="card-body">
                    @if (session('resent'))
                        <div class="alert alert-success" role="alert">
                            Un nuovo link di verifica è stato inviato al tuo indirizzo email.
                        </div>
                    @endif
                    Ti abbiamo inviato un'email con un link di verifica.
                    Se non ricevi l'email <a href="{{ route('verification.resend') }}">fai click qui per riceverne un'altra</a>.
                </div>
            </div>
        </div>
    </div>
</div>
@endsection
