<!doctype html>
<html lang="{{ app()->getLocale() }}">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Copiga</title>

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,600" rel="stylesheet" type="text/css">

        <!-- Styles -->
        <style>
            html, body {

                background-color: #0099cc;
                #color: #636b6f;

                font-family: 'Nunito', sans-serif;
                font-weight: 900;
                height: 500vh;
                margin: 0;
            }

            sfondo {
              background-image: url("/img/scrivania.jpg");
            }

            .full-height {
                height: 100vh;
            }

            .flex-center {
                align-items: center;
                display: flex;
                justify-content: center;
            }

            .position-ref {
                position: relative;
            }

            .top-right {
                position: absolute;
                right: 10px;
                top: 18px;
            }

            .content {
                text-align: center;
            }

            .title {
                font-size: 84px;
            }

            .links > a {
                color: #ffffff;
                padding: 0 25px;
                font-size: 12px;
                font-weight: 600;
                letter-spacing: .1rem;
                text-decoration: none;
                text-transform: uppercase;
            }

            .m-b-md {
                margin-bottom: 30px;
            }
        </style>
    </head>
    <body>
        <div class="flex-center position-ref full-height">
            @if (Route::has('login'))
                <div class="top-right links">
                    @auth
                        <a href="{{ url('/home') }}">Home</a>
                    @else
                        <a href="{{ route('login') }}">Login</a>
                        <a href="{{ route('register') }}">Registra</a>
                        <a href="{{ route('joinus') }}">PortaleCopisteria</a>
                    @endauth
                </div>
            @endif

            <div class="content">
                <div class="title m-b-md">
                    Copiga
                </div>

                <div>
                    Sei stanco di dovere fare file interminabili in copisteria quando devi stampare un file?<br>
                    Sei nel posto giusto, con noi puoi inviare il tuo file nella tua copisteria preferita ed essere avvisato quando questo è pronto per il ritiro.<br>
                    ~ Semplice, Comodo, Copiga!
                </div>
            </div>
        </div>
    </body>
</html>
