<?php

namespace App;

use Illuminate\Database\Eloquent\Model;

class audits extends Model
{
    public $table = 'audits';
    
    const CREATED_AT = 'created_at';
    const UPDATED_AT = 'updated_at';


    protected $dates = ['deleted_at'];
    protected $fillable = [
        
    ];

    /**
     * The attributes that should be hidden for arrays.
     *
     * @var array
     */
    protected $hidden = [
    ];
}
