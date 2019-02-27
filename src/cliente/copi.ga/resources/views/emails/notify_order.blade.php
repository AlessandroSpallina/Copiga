<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>{{ $title }}</title>
    <link href="{{ asset('css/app.css') }}" rel="stylesheet">
</head>
<body>
    <style>
        @media only screen and (max-width: 600px) {
            .inner-body {
                width: 100% !important;
            }

            .footer {
                width: 100% !important;
            }
        }

        @media only screen and (max-width: 500px) {
            .button {
                width: 100% !important;
            }
        }
    </style>

    <div class="container-fluid">
        <tr>
            <td align="center">
                <div class="content">
                    <!-- Email Body -->
                        <div class="body">
                            <div width="570" cellpadding="0" cellspacing="0">
                                <!-- Body content -->
                                <p>
                                      Ciao {{ $user['name'] }},<br>
                                      {!! $body !!}
                                </p>
                                <p>
                                      Di seguito il riepilogo del tuo ordine:<br>
                                      <b><a href="
                                      @php
                                        echo(asset('storage/'.$order['filename']));
                                      @endphp
                                      ">File</a></b>
                                      <br>

                                      <b>Fronte-Retro:</b>
                                      @php
                                        echo(($order['bothSides'] == true) ? 'si' : 'no');
                                      @endphp
                                      <br>

                                      <b>A Colori:</b>
                                      @php
                                        echo(($order['color'] == true) ? 'si' : 'no');
                                      @endphp
                                      <br>

                                      <b>Pagine per Lato:</b>
                                      @php
                                        echo((string) $order['pagesForSide']);
                                      @endphp
                                      <br>

                                      <b>Prezzo:</b>
                                      @php
                                        echo(((string) $order['price']).' €');
                                      @endphp
                                      <br>
                                </p>

                                <main class="py-4">
                                    © 2019 <a href="https://copi.ga">Copiga</a>. All rights reserved.
                                </main>
                            </div>
                        </div>
                    </tr>
                </div>
            </td>
        </tr>
    </div>
</body>
</html>
