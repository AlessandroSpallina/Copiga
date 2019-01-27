<?php

namespace App;

use Illuminate\Notifications\Notifiable;
use Illuminate\Foundation\Auth\User as Authenticatable;

class Printshop extends Authenticatable
{
    use Notifiable;

    protected $guard = 'printshop';

    protected $fillable = [
        'name', 'email', 'address', 'vatnumber', 'phone', 'password',
    ];

    protected $hidden = [
        'password', 'remember_token',
    ];
}
