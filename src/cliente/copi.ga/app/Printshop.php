<?php

namespace App;

use Tymon\JWTAuth\Contracts\JWTSubject;
use Illuminate\Notifications\Notifiable;
use Illuminate\Contracts\Auth\MustVerifyEmail;
use Illuminate\Foundation\Auth\User as Authenticatable;

class Printshop extends Authenticatable implements JWTSubject
{
    use Notifiable;

    protected $guard = 'printshop';

    protected $fillable = [
        'name', 'email', 'address', 'vatnumber', 'phone', 'password',
    ];

    protected $hidden = [
        'password', 'remember_token',
    ];

    /**
     * Get the identifier that will be stored in the subject claim of the JWT.
     *
     * @return mixed
     */
    public function getJWTIdentifier()
    {
        return $this->getKey();
    }


    /**
     * Return a key value array, containing any custom claims to be added to the JWT.
     *
     * @return array
     */
    public function getJWTCustomClaims()
    {
        return [];
    }


    public function files()
    {
        return $this->hasMany('App\File');
    }

    public function papers()
    {
        return $this->hasMany('App\Paper');
    }

    public function bookbindings()
    {
        return $this->hasMany('App\Bookbinding');
    }

    public function credits()
    {
      return $this->hasMany('App\Credit');
    }

}
