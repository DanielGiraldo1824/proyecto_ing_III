<?php

namespace App\Http\Controllers\API;

use App\Http\Requests\API\CreateproceduresAPIRequest;
use App\Http\Requests\API\UpdateproceduresAPIRequest;
use App\Models\procedures;
use Illuminate\Support\Facades\DB;
use App\Repositories\proceduresRepository;
use Illuminate\Http\Request;
use App\Http\Controllers\AppBaseController;
use InfyOm\Generator\Criteria\LimitOffsetCriteria;
use Prettus\Repository\Criteria\RequestCriteria;
use App\Http\Controllers\UtilsController;
use Response;

/**
 * Class proceduresController
 * @package App\Http\Controllers\API
 */

class proceduresAPIController extends AppBaseController
{
    /** @var  proceduresRepository */
    private $proceduresRepository;

    public function __construct(proceduresRepository $proceduresRepo)
    {
        $this->proceduresRepository = $proceduresRepo;
    }

    /**
     * @param Request $request
     * @return Response
     *
     * @SWG\Get(
     *      path="/procedures",
     *      summary="Get a listing of the procedures.",
     *      tags={"procedures"},
     *      description="Get all procedures",
     *      produces={"application/json"},
     *      @SWG\Response(
     *          response=200,
     *          description="successful operation",
     *          @SWG\Schema(
     *              type="object",
     *              @SWG\Property(
     *                  property="success",
     *                  type="boolean"
     *              ),
     *              @SWG\Property(
     *                  property="data",
     *                  type="array",
     *                  @SWG\Items(ref="#/definitions/procedures")
     *              ),
     *              @SWG\Property(
     *                  property="message",
     *                  type="string"
     *              )
     *          )
     *      )
     * )
     */
    public function index(Request $request)
    {
        $this->proceduresRepository->pushCriteria(new RequestCriteria($request));
        $this->proceduresRepository->pushCriteria(new LimitOffsetCriteria($request));
        $procedures = $this->proceduresRepository->all();

        return $this->sendResponse($procedures->toArray(), 'Procedures retrieved successfully');
    }

    /**
     * @param CreateproceduresAPIRequest $request
     * @return Response
     *
     * @SWG\Post(
     *      path="/procedures",
     *      summary="Store a newly created procedures in storage",
     *      tags={"procedures"},
     *      description="Store procedures",
     *      produces={"application/json"},
     *      @SWG\Parameter(
     *          name="body",
     *          in="body",
     *          description="procedures that should be stored",
     *          required=false,
     *          @SWG\Schema(ref="#/definitions/procedures")
     *      ),
     *      @SWG\Response(
     *          response=200,
     *          description="successful operation",
     *          @SWG\Schema(
     *              type="object",
     *              @SWG\Property(
     *                  property="success",
     *                  type="boolean"
     *              ),
     *              @SWG\Property(
     *                  property="data",
     *                  ref="#/definitions/procedures"
     *              ),
     *              @SWG\Property(
     *                  property="message",
     *                  type="string"
     *              )
     *          )
     *      )
     * )
     */
    public function store(CreateproceduresAPIRequest $request)
    {
        $input = $request->all();
        //return $request->patient_id;
        $input['patient_id'] = DB::table('patient')->where('tag_id', $request->patient_id)->first()->id;
         
        
        $procedures = $this->proceduresRepository->create($input);

        return $this->sendResponse($procedures->toArray(), 'Procedures saved successfully');
    }

    /**
     * @param int $id
     * @return Response
     *
     * @SWG\Get(
     *      path="/procedures/{id}",
     *      summary="Display the specified procedures",
     *      tags={"procedures"},
     *      description="Get procedures",
     *      produces={"application/json"},
     *      @SWG\Parameter(
     *          name="id",
     *          description="id of procedures",
     *          type="integer",
     *          required=true,
     *          in="path"
     *      ),
     *      @SWG\Response(
     *          response=200,
     *          description="successful operation",
     *          @SWG\Schema(
     *              type="object",
     *              @SWG\Property(
     *                  property="success",
     *                  type="boolean"
     *              ),
     *              @SWG\Property(
     *                  property="data",
     *                  ref="#/definitions/procedures"
     *              ),
     *              @SWG\Property(
     *                  property="message",
     *                  type="string"
     *              )
     *          )
     *      )
     * )
     */
    public function show($id)
    {
        /** @var procedures $procedures */
        //$procedures = $this->proceduresRepository->findWithoutFail($id);
        $patient = DB::table('patient')->where('tag_id', $id)->first()->id;
        
        $procedures = $this->proceduresRepository->findByField("patient_id", $patient);
        //return $procedures['users_id'];
        foreach ($procedures as $item){  
            $item['users'] = DB::table('users')->where('id', $item['users_id'])->first()->name;
            $item['patient'] = DB::table('patient')->where('tag_id', $id)->first()->name;
            $item['centers'] = DB::table('centers')->where('id', $item['centers_id'])->first()->name;
            //$item['date'] = UtilsController::separateTimestamp($item['created_at']);
        }
        
        
        if (empty($procedures)) {
            return $this->sendError('Procedures not found');
        }

        return $this->sendResponse($procedures, 'Procedures retrieved successfully');
    }

    /**
     * @param int $id
     * @param UpdateproceduresAPIRequest $request
     * @return Response
     *
     * @SWG\Put(
     *      path="/procedures/{id}",
     *      summary="Update the specified procedures in storage",
     *      tags={"procedures"},
     *      description="Update procedures",
     *      produces={"application/json"},
     *      @SWG\Parameter(
     *          name="id",
     *          description="id of procedures",
     *          type="integer",
     *          required=true,
     *          in="path"
     *      ),
     *      @SWG\Parameter(
     *          name="body",
     *          in="body",
     *          description="procedures that should be updated",
     *          required=false,
     *          @SWG\Schema(ref="#/definitions/procedures")
     *      ),
     *      @SWG\Response(
     *          response=200,
     *          description="successful operation",
     *          @SWG\Schema(
     *              type="object",
     *              @SWG\Property(
     *                  property="success",
     *                  type="boolean"
     *              ),
     *              @SWG\Property(
     *                  property="data",
     *                  ref="#/definitions/procedures"
     *              ),
     *              @SWG\Property(
     *                  property="message",
     *                  type="string"
     *              )
     *          )
     *      )
     * )
     */
    public function update($id, UpdateproceduresAPIRequest $request)
    {
        $input = $request->all();

        /** @var procedures $procedures */
        $procedures = $this->proceduresRepository->findWithoutFail($id);

        if (empty($procedures)) {
            return $this->sendError('Procedures not found');
        }

        $procedures = $this->proceduresRepository->update($input, $id);

        return $this->sendResponse($procedures->toArray(), 'procedures updated successfully');
    }

    /**
     * @param int $id
     * @return Response
     *
     * @SWG\Delete(
     *      path="/procedures/{id}",
     *      summary="Remove the specified procedures from storage",
     *      tags={"procedures"},
     *      description="Delete procedures",
     *      produces={"application/json"},
     *      @SWG\Parameter(
     *          name="id",
     *          description="id of procedures",
     *          type="integer",
     *          required=true,
     *          in="path"
     *      ),
     *      @SWG\Response(
     *          response=200,
     *          description="successful operation",
     *          @SWG\Schema(
     *              type="object",
     *              @SWG\Property(
     *                  property="success",
     *                  type="boolean"
     *              ),
     *              @SWG\Property(
     *                  property="data",
     *                  type="string"
     *              ),
     *              @SWG\Property(
     *                  property="message",
     *                  type="string"
     *              )
     *          )
     *      )
     * )
     */
    public function destroy($id)
    {
        /** @var procedures $procedures */
        $procedures = $this->proceduresRepository->findWithoutFail($id);

        if (empty($procedures)) {
            return $this->sendError('Procedures not found');
        }

        $procedures->delete();

        return $this->sendResponse($id, 'Procedures deleted successfully');
    }
}
