@extends('layouts.app_user')

@section('content')
<div class="container">
    <div class="row justify-content-center">
        <div class="col-md-12">
            <div id='firstPage' class="card">
                <div class="card-header">Crea un <b>Nuovo Ordine</b></div>
                  <div class="card-body">
                      <h4 class="card-title">Seleziona Copisteria</h4>
                      <br>
                      <h6 class="card-subtitle mb-2 text-muted">Seleziona con un click la copisteria alla quale vuoi inviare la tua stampa.</h6>
                      <h6 class="card-subtitle mb-2 text-muted">Se il bottone è verde vuol dire che è selezionato.</h6>
                      <br>
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
                                    <td>Copisteria</td>
                                    <td>Credito</td>
                                    <td colspan="2">Azioni</td>
                                  </tr>
                              </thead>
                              <tbody>
                                @foreach($credits as $credit)
                                <tr>
                                    <td><button type="button" class="btn btn-outline-dark" style="margin:1px" id="c{{ $credit->id }}" onClick="addCreditSelection({{ $credit->id }})">{{ $credit->name }}</button></td>
                                    <td>{{$credit->disponibile}} €</td>
                                    <td><button class="btn btn-outline-secondary">Prezzi</button></td>
                                </tr>
                                @endforeach
                              </tbody>
                            </table>
                      </div>
                    </div>
                    <button class="btn btn-primary float-right" onClick="next()">Avanti</button>
                  </div>
                </div>

                <div id='secondPage' class="card" hidden>
                  <div class="card-header">Crea un <b>Nuovo Ordine</b></div>
                  <div class="card-body">
                    <h4 class="card-title">Upload File e Specifiche di Stampa</h4>
                    <br>
                    <h6 class="card-subtitle mb-2 text-muted">Carica il file che vuoi stampare e seleziona le impostazioni di stampa.</h6>
                    <br>
                      <div class="uper">
                        <form method="POST" action="{{ route('order') }}" enctype="multipart/form-data">
                          @csrf
                          <input type="text" id="selezione_copisteria" name="selezione_copisteria" hidden>

                          <div class="form-group">
                            <input type="file" class="form-control-file" name="fileUpload" id="fileUpload">
                          </div>

                          <div class="form-group">
                            <label for="paper">Seleziona la tipologia di carta desiderata.</label>
                            <select id="paper" name="paper" class="custom-select">
                              <option selected disabled>Tipi di Carta</option>
                            </select>
                          </div>

                          <div class="form-group">
                            <label for="bookbinding">Seleziona la tipologia di rilegatura desiderata.</label>
                            <select id="bookbinding" name="bookbinding" class="custom-select">
                              <option selected disabled>Tipi di Rilegatura</option>
                            </select>
                          </div>

                          <div class="form-group">
                            <div class="custom-control custom-radio">
                              <input type="radio" id="bothSides1" name="bothSides" class="custom-control-input" value="yes" checked>
                              <label class="custom-control-label" for="bothSides1">Fronto-Retro</label>
                            </div>
                            <div class="custom-control custom-radio">
                              <input type="radio" id="bothSides2" name="bothSides" class="custom-control-input" value="no">
                              <label class="custom-control-label" for="bothSides2">Solo Fronte</label>
                            </div>
                          </div>

                          <div class="form-group">
                            <div class="custom-control custom-radio">
                              <input type="radio" id="color1" name="color" class="custom-control-input" value="no" checked>
                              <label class="custom-control-label" for="color1">Bianco e Nero</label>
                            </div>
                            <div class="custom-control custom-radio">
                              <input type="radio" id="color2" name="color" class="custom-control-input" value="yes">
                              <label class="custom-control-label" for="color2">A Colori</label>
                            </div>
                          </div>

                          <div class="form-group">
                            <label for="wrap">Seleziona il numero di pagine del file caricato per ogni lato del foglio.</label>
                            <div id="wrap">
                              <div class="custom-control custom-radio">
                                <input type="radio" id="pagesForSide1" name="pagesForSide" class="custom-control-input" value="1" checked>
                                <label class="custom-control-label" for="pagesForSide1">1</label>
                              </div>
                              <div class="custom-control custom-radio">
                                <input type="radio" id="pagesForSide2" name="pagesForSide" class="custom-control-input" value="2">
                                <label class="custom-control-label" for="pagesForSide2">2</label>
                              </div>
                              <div class="custom-control custom-radio">
                                <input type="radio" id="pagesForSide3" name="pagesForSide" class="custom-control-input" value="3">
                                <label class="custom-control-label" for="pagesForSide3">3</label>
                              </div>
                              <div class="custom-control custom-radio">
                                <input type="radio" id="pagesForSide4" name="pagesForSide" class="custom-control-input" value="4">
                                <label class="custom-control-label" for="pagesForSide4">4</label>
                              </div>
                            </div>
                          </div>



                          <input class="btn btn-primary float-right" type="submit" value="Invia">
                        </form>
                      </div>
                  </div>
                </div>




</div>
@endsection

<script>

function next()
{
  var data = {!! json_encode($credits) !!};

  if(!document.getElementById("selezione_copisteria").value) {
    alert('Devi selezionare una Copisteria prima!');
  } else {
    for (let i=0; i<data.length; i++) {
      if(data[i]['id'] == document.getElementById("selezione_copisteria").value) {
        data[i]['papers'].forEach((elem) => {
          document.getElementById("paper").add(new Option(elem['formato'], elem['id']));
        });
        document.getElementById("paper").options[1].selected = true;

        data[i]['bookbindings'].forEach((elem) => {
          document.getElementById("bookbinding").add(new Option(elem['tipo'], elem['id']));
        });
        document.getElementById("bookbinding").options[1].selected = true;
        break;
      }
    }

    document.getElementById("firstPage").hidden = true;
    document.getElementById("secondPage").hidden = false;

  }
}

function addCreditSelection(credit_id)
{
  var button = document.getElementById("c" + credit_id).classList;
  var selector = document.getElementById("selezione_copisteria");
  if(button.contains("btn-outline-dark")) { //se qui : è appena stato selezionato
    button.remove("btn-outline-dark");
    button.add("btn-success");
    // il valore nell'entry selettore non c'è e si deve sostituire
    if(selector.value != "") {
      var tmp = document.getElementById("selezione_copisteria").value;
      var button = document.getElementById("c" + tmp).classList;
      button.remove("btn-success");
      button.add("btn-outline-dark");
    }
    selector.value = credit_id;

  } else {
    button.remove("btn-success");
    button.add("btn-outline-dark");
    // il valore nell'entry selettore c'è e si deve togliere
    selector.value = "";

  }
}

</script>
