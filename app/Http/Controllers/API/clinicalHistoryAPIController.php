<?php

namespace App\Http\Controllers\API;

use App\Http\Requests\API\CreateclinicalHistoryAPIRequest;
use App\Http\Requests\API\UpdateclinicalHistoryAPIRequest;
use App\Models\clinicalHistory;
use App\Models\patient;
use Illuminate\Support\Facades\DB;
use App\Repositories\clinicalHistoryRepository;
use Illuminate\Http\Request;
use App\Http\Controllers\AppBaseController;
use InfyOm\Generator\Criteria\LimitOffsetCriteria;
use Prettus\Repository\Criteria\RequestCriteria;
use Response;

/**
 * Class clinicalHistoryController
 * @package App\Http\Controllers\API
 */

class clinicalHistoryAPIController extends AppBaseController
{
    /** @var  clinicalHistoryRepository */
    private $clinicalHistoryRepository;

    public function __construct(clinicalHistoryRepository $clinicalHistoryRepo)
    {
        $this->clinicalHistoryRepository = $clinicalHistoryRepo;
    }

    /**
     * @param Request $request
     * @return Response
     *
     * @SWG\Get(
     *      path="/clinicalHistories",
     *      summary="Get a listing of the clinicalHistories.",
     *      tags={"clinicalHistory"},
     *      description="Get all clinicalHistories",
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
     *                  @SWG\Items(ref="#/definitions/clinicalHistory")
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
        $this->clinicalHistoryRepository->pushCriteria(new RequestCriteria($request));
        $this->clinicalHistoryRepository->pushCriteria(new LimitOffsetCriteria($request));
        $clinicalHistories = $this->clinicalHistoryRepository->all();
       
        return $this->sendResponse($clinicalHistories, 'Clinical Histories retrieved successfully');
    }

    /**
     * @param CreateclinicalHistoryAPIRequest $request
     * @return Response
     *
     * @SWG\Post(
     *      path="/clinicalHistories",
     *      summary="Store a newly created clinicalHistory in storage",
     *      tags={"clinicalHistory"},
     *      description="Store clinicalHistory",
     *      produces={"application/json"},
     *      @SWG\Parameter(
     *          name="body",
     *          in="body",
     *          description="clinicalHistory that should be stored",
     *          required=false,
     *          @SWG\Schema(ref="#/definitions/clinicalHistory")
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
     *                  ref="#/definitions/clinicalHistory"
     *              ),
     *              @SWG\Property(
     *                  property="message",
     *                  type="string"
     *              )
     *          )
     *      )
     * )
     */
    public function store(CreateclinicalHistoryAPIRequest $request)
    {
        $input = $request->all();

        $clinicalHistories = $this->clinicalHistoryRepository->create($input);

        return $this->sendResponse($clinicalHistories->toArray(), 'Clinical History saved successfully');
    }

    /**
     * @param int $id
     * @return Response
     *
     * @SWG\Get(
     *      path="/clinicalHistories/{id}",
     *      summary="Display the specified clinicalHistory",
     *      tags={"clinicalHistory"},
     *      description="Get clinicalHistory",
     *      produces={"application/json"},
     *      @SWG\Parameter(
     *          name="id",
     *          description="id of clinicalHistory",
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
     *                  ref="#/definitions/clinicalHistory"
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
        /** @var clinicalHistory $clinicalHistory */
        //$clinicalHistory = $this->clinicalHistoryRepository->findWithoutFail($id);
        //$patient = patient::where('tag_id', $id);
         $patient = DB::table('patient')->where('tag_id', $id)->first()->id;
        //$patient_id = patient::where('tag_id', $id)->id;
        $clinicalHistory = $this->clinicalHistoryRepository->findByField("patient_id", $patient);
            
//        foreach ($clinicalHistories as $item){   
//          $city = DB::table('city')->get();  
//        }
        //$users = DB::table('users')->get();

        if (empty($clinicalHistory)) {
            return $this->sendError('Clinical History not found');
        }

        return $this->sendResponse($clinicalHistory, 'Clinical History retrieved successfully');
    }

    /**
     * @param int $id
     * @param UpdateclinicalHistoryAPIRequest $request
     * @return Response
     *
     * @SWG\Put(
     *      path="/clinicalHistories/{id}",
     *      summary="Update the specified clinicalHistory in storage",
     *      tags={"clinicalHistory"},
     *      description="Update clinicalHistory",
     *      produces={"application/json"},
     *      @SWG\Parameter(
     *          name="id",
     *          description="id of clinicalHistory",
     *          type="integer",
     *          required=true,
     *          in="path"
     *      ),
     *      @SWG\Parameter(
     *          name="body",
     *          in="body",
     *          description="clinicalHistory that should be updated",
     *          required=false,
     *          @SWG\Schema(ref="#/definitions/clinicalHistory")
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
     *                  ref="#/definitions/clinicalHistory"
     *              ),
     *              @SWG\Property(
     *                  property="message",
     *                  type="string"
     *              )
     *          )
     *      )
     * )
     */
    public function update($id, UpdateclinicalHistoryAPIRequest $request)
    {
        $input = $request->all();

        /** @var clinicalHistory $clinicalHistory */
        $clinicalHistory = $this->clinicalHistoryRepository->findWithoutFail($id);

        if (empty($clinicalHistory)) {
            return $this->sendError('Clinical History not found');
        }

        $clinicalHistory = $this->clinicalHistoryRepository->update($input, $id);

        return $this->sendResponse($clinicalHistory->toArray(), 'clinicalHistory updated successfully');
    }

    /**
     * @param int $id
     * @return Response
     *
     * @SWG\Delete(
     *      path="/clinicalHistories/{id}",
     *      summary="Remove the specified clinicalHistory from storage",
     *      tags={"clinicalHistory"},
     *      description="Delete clinicalHistory",
     *      produces={"application/json"},
     *      @SWG\Parameter(
     *          name="id",
     *          description="id of clinicalHistory",
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
        /** @var clinicalHistory $clinicalHistory */
        $clinicalHistory = $this->clinicalHistoryRepository->findWithoutFail($id);

        if (empty($clinicalHistory)) {
            return $this->sendError('Clinical History not found');
        }

        $clinicalHistory->delete();

        return $this->sendResponse($id, 'Clinical History deleted successfully');
    }
}
