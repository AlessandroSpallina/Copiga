<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class Credit extends Model
{

  protected $fillable = [
      'disponibile',
      'totale'
  ];


  public function prinshop()
  {
    return $this->belongsTo('App\Printshop');
  }

  public function user()
  {
    return $this->belongsTo('App\User');
  }

  public function orders()
  {
      return $this->hasMany('App\Order');
  }

}
