<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class File extends Model
{
    protected $fillable = [
      'categoria',
      'estensione',
      'selezionato',
    ];
}
