<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Bookbinding extends Model
{
    protected $fillable = [
    'tipo',
    'estensione',
    'prezzo',
    'selezionato'
    ];
}
