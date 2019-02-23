<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Paper extends Model
{
    protected $fillable = [
    'formato',
    'descrizione',
    'prezzoBN',
    'prezzoC',
    'selezionato'
    ];

    public function printshop()
    {
        return $this->belongsTo('App\Printshop');
    }
}
