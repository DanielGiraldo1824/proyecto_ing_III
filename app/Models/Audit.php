<?php

namespace App\Models;

use Eloquent as Model;

/**
 * @SWG\Definition(
 *      definition="Audit",
 *      required={""},
 *      @SWG\Property(
 *          property="id",
 *          description="id",
 *          type="integer",
 *          format="int32"
 *      ),
 *      @SWG\Property(
 *          property="user_id",
 *          description="user_id",
 *          type="integer",
 *          format="int32"
 *      ),
 *      @SWG\Property(
 *          property="event",
 *          description="event",
 *          type="string"
 *      ),
 *      @SWG\Property(
 *          property="auditable_id",
 *          description="auditable_id",
 *          type="integer",
 *          format="int32"
 *      ),
 *      @SWG\Property(
 *          property="auditable_type",
 *          description="auditable_type",
 *          type="string"
 *      ),
 *      @SWG\Property(
 *          property="old_values",
 *          description="old_values",
 *          type="string"
 *      ),
 *      @SWG\Property(
 *          property="new_values",
 *          description="new_values",
 *          type="string"
 *      ),
 *      @SWG\Property(
 *          property="url",
 *          description="url",
 *          type="string"
 *      ),
 *      @SWG\Property(
 *          property="ip_address",
 *          description="ip_address",
 *          type="string"
 *      ),
 *      @SWG\Property(
 *          property="user_agent",
 *          description="user_agent",
 *          type="string"
 *      )
 * )
 */
class Audit extends Model
{
    public $table = 'audits';
    
    const CREATED_AT = 'created_at';
    const UPDATED_AT = 'updated_at';

    public $fillable = [
        'user_id',
        'event',
        'auditable_id',
        'auditable_type',
        'old_values',
        'new_values',
        'url',
        'ip_address',
        'user_agent'
    ];

    /**
     * The attributes that should be casted to native types.
     *
     * @var array
     */
    protected $casts = [
        'id' => 'integer',
        'user_id' => 'integer',
        'event' => 'string',
        'auditable_id' => 'integer',
        'auditable_type' => 'string',
        'old_values' => 'string',
        'new_values' => 'string',
        'url' => 'string',
        'ip_address' => 'string',
        'user_agent' => 'string'
    ];

    /**
     * Validation rules
     *
     * @var array
     */
    public static $rules = [
        
    ];

    
}
