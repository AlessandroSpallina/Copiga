<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Order extends Model
{

    protected $fillable = [
      'filename',
      'filehash',
      'filesize',
      'bothSides',
      'color',
      'pagesForSide',
      'price',
      'confirmed'
    ];

    public function credit()
    {
        return $this->belongsTo('App\Credit');
    }
}
