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

    public function printshop()
    {
        return $this->belongsTo('App\Printshop');
    }
}
