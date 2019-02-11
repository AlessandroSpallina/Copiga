@extends('layouts.app_printshop')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div class="card">
                <div class="card-header">Di seguito puoi scegliere le <b>Specifiche di Stampa</b> visibili dai tuoi clienti e il tuo <b>Tariffario</b></div>
                <div class="card-body">
                  @if(!empty($success))
                    <div class="alert alert-success">
                      {{ $success }}
                    </div>
                  @endif
                  <ul class="list-group list-group-flush">
                    <li class="list-group-item">
                      <h4 class="card-title">Tipi di File</h4>
                      <br>
                      <h6 class="card-subtitle mb-2 text-muted">Seleziona con un click i tipi di file che desideri ricevere dai tuoi clienti.</h6>
                      <h6 class="card-subtitle mb-2 text-muted">Se il bottone è verde vuol dire che è selezionato.</h6>
                      @php
                        $selezione_file = "";
                      @endphp
                      @foreach($files as $file)
                        @if($file->selezionato)
                          @php
                            $selezione_file = $selezione_file.$file->id."|";
                          @endphp
                          <button type="button" class="btn btn-success" id="f{{ $file->id }}" onClick="addFileSelection({{ $file->id }})">{{ $file->categoria }}</button>
                        @else
                          <button type="button" class="btn btn-outline-dark" id="f{{ $file->id }}" onClick="addFileSelection({{ $file->id }})">{{ $file->categoria }}</button>
                        @endif
                      @endforeach
                    </li>
                    <li class="list-group-item">
                      <h4 class="card-title">Tipi di Carta</h4>
                      <br>
                      <h6 class="card-subtitle mb-2 text-muted">Seleziona i tipi di carta che desideri proporre ai tuoi clienti e i relativi prezzi</h6>
                      <h6 class="card-subtitle mb-2 text-muted">Se il bottone è verde vuol dire che è selezionato.</h6>

                      @php
                        $selezione_carta = "";
                      @endphp
                      @foreach($papers as $paper)
                      <div class="input-group">
                        <div class="input-group-prepend">
                        @if($paper->selezionato)
                          @php
                            $selezione_carta = $selezione_carta.$paper->id."|";
                          @endphp
                            <button type="button" class="btn btn-success" style="margin:1px" id="p{{ $paper->id }}" onClick="addPaperSelection({{ $paper->id }})">{{ $paper->formato }}</button>
                        @else
                            <button type="button" class="btn btn-outline-dark" style="margin:1px" id="p{{ $paper->id }}" onClick="addPaperSelection({{ $paper->id }})">{{ $paper->formato }}</button>
                        @endif

                        </div>
                        <input type="number" step=0.01 min="0.01" class="form-control" placeholder="€ Prezzo Bianco e Nero" id="p{{ $paper->id }}_bn" onblur="addPaperPriceBN({{ $paper->id }})" disabled>
                        <input type="number" step=0.01 min="0.01" class="form-control" placeholder="€ Prezzo a Colori" id="p{{ $paper->id }}_c" onblur="addPaperPriceC({{ $paper->id }})" disabled>
                      </div>
                      @endforeach



                    </li>
                    <li class="list-group-item">
                      <h4 class="card-title">Tipi di Rilegatura</h4>
                      <h6 class="card-subtitle mb-2 text-muted">Seleziona i tipi di rilegatura che desideri proporre ai tuoi clienti e i relativi prezzi</h6>



                    </li>
                    <li class="list-group-item">
                      <form method="POST" action="{{ route('printspecs') }}">
                        @csrf

                        <input type="text" id="selezione_file" name="selezione_file" value="{{ $selezione_file }}">

                        <input type="text" id="selezione_carta" name="selezione_carta" value="{{ $selezione_carta }}">
                        <input type="text" id="prezzoBN_carta" name="prezzoBN_carta">
                        <input type="text" id="prezzoC_carta" name="prezzoC_carta">

                        <input type="text" id="selezione_rilegatura" name="selezione_rilegatura">
                        <input type="text" id="prezzo_rilegatura" name="prezzo_rilegatura">

                        <input class="btn btn-primary float-right" type="submit" value="Salva Configurazione">
                      </form>
                    </li>
                  </ul>
                </div>
            </div>
        </div>
    </div>
</div>
@endsection

<script>
function __removeSubstr(source, toremove)
{
  var tmp_array = source.slice(0, -1).split("|");
  var index = tmp_array.indexOf(toremove.toString());
  if(index > -1) {
    var ret = "";
    tmp_array.splice(index, 1);
    tmp_array.forEach((elem) => {
      ret += elem + "|"
    });
    return ret;
  } else {
    return source;
  }
}

function addFileSelection(file_id)
{
  var button = document.getElementById("f" + file_id).classList;
  var selector = document.getElementById("selezione_file");
  if(button.contains("btn-outline-dark")) { //se qui : è appena stato selezionato
    button.remove("btn-outline-dark");
    button.add("btn-success");
    // il valore nell'entry selettore non c'è e si deve aggiungere
    selector.value += file_id + "|"
  } else {
    button.remove("btn-success");
    button.add("btn-outline-dark");
    // il valore nell'entry selettore c'è e si deve togliere
    selector.value = __removeSubstr(selector.value, file_id);
  }
}

function addPaperSelection(paper_id)
{
  var button = document.getElementById("p" + paper_id).classList;
  var selector = document.getElementById("selezione_carta");
  if(button.contains("btn-outline-dark")) { //se qui : è appena stato selezionato
    button.remove("btn-outline-dark");
    button.add("btn-success");
    // il valore nell'entry selettore non c'è e si deve aggiungere
    selector.value += paper_id + "|";
    document.getElementById("p" + paper_id + "_bn").disabled = false;
    document.getElementById("p" + paper_id + "_c").disabled = false;
  } else {
    button.remove("btn-success");
    button.add("btn-outline-dark");
    // il valore nell'entry selettore c'è e si deve togliere
    selector.value = __removeSubstr(selector.value, paper_id);
    document.getElementById("p" + paper_id + "_bn").disabled = true;
    document.getElementById("p" + paper_id + "_bn").value = "";
    document.getElementById("p" + paper_id + "_c").disabled = true;
    document.getElementById("p" + paper_id + "_c").value = "";
  }
}

function addPaperPriceBN(paper_id)
{
  document.getElementById("prezzoBN_carta").value += paper_id + ":" + document.getElementById("p" + paper_id + "_bn").value + "|";
}

function addPaperPriceC(paper_id)
{
  document.getElementById("prezzoC_carta").value += paper_id + ":" + document.getElementById("p" + paper_id + "_c").value + "|";
}

window.onload = function() {
  if(document.getElementById("selezione_carta").value != "") {
    var p_array = document.getElementById("selezione_carta").value.slice(0, -1).split('|');
    p_array.forEach((elem) => {
      document.getElementById("p" + elem + "_bn").disabled = false;
      document.getElementById("p" + elem + "_c").disabled = false;
    });
  }
}
</script>
