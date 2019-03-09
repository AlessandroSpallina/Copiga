<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Order extends Model
{

    protected $fillable = [
      'filename',
      'filehash',
      'filesize',
      'paper_id',
      'bookbinding_id',
      'bothSides',
      'color',
      'pagesForSide',
    ];

    public function credit()
    {
        return $this->belongsTo('App\Credit');
    }
}
