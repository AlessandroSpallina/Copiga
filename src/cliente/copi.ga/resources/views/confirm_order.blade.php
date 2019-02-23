@extends('layouts.app_user')

@section('content')

<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Conferma il tuo <b>Ordine</b></div>
                  <div class="card-body">
                      <h4 class="card-title">Riepilogo</h4>
                      <br>
                      <p>
                        <b>Creato:</b> {{ $order['created_at'] }}<br>
                        <b>File:</b> <a href="{{ $order['link'] }}" target="_blank">{{ $order['filename'] }}</a><br>
                        <b>Hash MD5:</b> {{ $order['md5'] }}<br>
                        <b>Dimensione:</b> {{ $order['size'] }} kB<br>
                        <b>Carta:</b> {{ $order['paper'] }}<br>
                        <b>Rilegatura:</b> {{ $order['bookbinding'] }}<br>
                        <b>Fronte-Retro:</b> {{ ($order['bothSides'] == true) ? 'si' : 'no' }}<br>
                        <b>A Colori:</b> {{ ($order['color'] == true) ? 'si' : 'no' }}<br>
                        <b>Pagine per Lato:</b> {{ $order['pagesForSide'] }}<br>
                        <b>Costo totale:</b> {{ $order['price'] }} €
                      </p>


                      <form method="POST" action="{{ route('order/confirm') }}">
                        @csrf
                        <input type="text" id="action" name="action" hidden>
                        <input type="text" id="order" name="order" value="{{ $order['id'] }}" hidden>
                        <input class="btn btn-success float-right" style="margin:2px" type="submit" onClick="confirm()" value="Conferma">
                        <input class="btn btn-danger float-right" style="margin:2px" type="submit" onClick="abort()" value="Annulla">
                      </form>
                      </div>
                    </div>
                  </div>
                </div>
</div>
@endsection

<script>
function abort()
{
  document.getElementById("action").value = 'abort';
}

function confirm()
{
  document.getElementById("action").value = 'confirm';
}

</script>
