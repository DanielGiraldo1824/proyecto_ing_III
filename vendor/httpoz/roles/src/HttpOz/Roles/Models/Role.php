<?php

namespace HttpOz\Roles\Models;


use HttpOz\Roles\Traits\Sluggable;
use Illuminate\Database\Eloquent\Model;
use Illuminate\Database\Eloquent\SoftDeletes;
use HttpOz\Roles\Traits\RoleHasRelations;
use HttpOz\Roles\Contracts\RoleHasRelations as RoleHasRelationsContract;
use OwenIt\Auditing\Auditable;
use OwenIt\Auditing\Contracts\Auditable as AuditableContract;

class Role extends Model implements RoleHasRelationsContract
{
    use Sluggable, RoleHasRelations,SoftDeletes, Auditable;

    /**
     * The attributes that are mass assignable.
     *
     * @var array
     */
    protected $fillable = ['name', 'slug', 'description', 'group'];

    /**
     * Create a new model instance.
     *
     * @param array $attributes
     * @return void
     */
    public function __construct(array $attributes = [])
    {
        parent::__construct($attributes);
        if ($connection = config('roles.connection')) {
            $this->connection = $connection;
        }
    }

}