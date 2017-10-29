<?php

namespace App\Models;

use Eloquent as Model;
use Illuminate\Database\Eloquent\SoftDeletes;
use OwenIt\Auditing\Auditable;
use OwenIt\Auditing\Contracts\Auditable as AuditableContract;

/**
 * @SWG\Definition(
 *      definition="centers",
 *      required={""},
 *      @SWG\Property(
 *          property="idcenters",
 *          description="idcenters",
 *          type="integer",
 *          format="int32"
 *      ),
 *      @SWG\Property(
 *          property="name",
 *          description="name",
 *          type="string"
 *      )
 * )
 */
class centers extends Model implements AuditableContract
{
    use SoftDeletes;
    use Auditable;
    public $table = 'centers';
    
    const CREATED_AT = 'created_at';
    const UPDATED_AT = 'updated_at';


    protected $dates = ['deleted_at'];


    public $fillable = [
        'name_center'
    ];

    /**
     * The attributes that should be casted to native types.
     *
     * @var array
     */
    protected $casts = [
        'id' => 'integer',
        'name_center' => 'string'
    ];

    /**
     * Validation rules
     *
     * @var array
     */
    public static $rules = [
        'name_center' => 'required'
    ];

    /**
     * @return \Illuminate\Database\Eloquent\Relations\HasMany
     **/
    public function patients()
    {
        return $this->hasMany(\App\Models\patient::class);
    }
    
    public function users()
    {
        return $this->hasMany(\App\Models\users::class)->withTrashed();
    }
}
