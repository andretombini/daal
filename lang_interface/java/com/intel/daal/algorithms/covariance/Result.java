/* file: Result.java */
/*******************************************************************************
* Copyright 2014-2018 Intel Corporation.
*
* This software and the related documents are Intel copyrighted  materials,  and
* your use of  them is  governed by the  express license  under which  they were
* provided to you (License).  Unless the License provides otherwise, you may not
* use, modify, copy, publish, distribute,  disclose or transmit this software or
* the related documents without Intel's prior written permission.
*
* This software and the related documents  are provided as  is,  with no express
* or implied  warranties,  other  than those  that are  expressly stated  in the
* License.
*******************************************************************************/

/**
 * @ingroup covariance
 * @{
 */
package com.intel.daal.algorithms.covariance;

import com.intel.daal.algorithms.ComputeMode;
import com.intel.daal.algorithms.ComputeStep;
import com.intel.daal.algorithms.Precision;
import com.intel.daal.data_management.data.Factory;
import com.intel.daal.data_management.data.NumericTable;
import com.intel.daal.services.DaalContext;

/**
 * <a name="DAAL-CLASS-ALGORITHMS__COVARIANCE__RESULT"></a>
 * @brief Provides methods to access the results obtained with the compute() method of the
 *        correlation or variance-covariance matrix algorithm in the batch processing mode
 */
public final class Result extends com.intel.daal.algorithms.Result {
    /** @private */
    static {
        System.loadLibrary("JavaAPI");
    }

    /**
     * Constructs the result of the correlation or variance-covariance matrix algorithm
     * @param context   Context to manage the result of the correlation or variance-covariance matrix algorithm
     */
    public Result(DaalContext context) {
        super(context);
        this.cObject = cNewResult();
    }

    public Result(DaalContext context, long cResult) {
        super(context, cResult);
    }

    /**
     * Returns the result of the correlation or variance-covariance matrix algorithm in the batch processing mode
     * @param id   Identifier of the result, @ref ResultId
     * @return Result that corresponds to the given identifier
     */
    public NumericTable get(ResultId id) {
        if (id != ResultId.covariance && id != ResultId.correlation && id != ResultId.mean) {
            throw new IllegalArgumentException("id unsupported");
        }
        return (NumericTable)Factory.instance().createObject(getContext(), cGetResultTable(cObject, id.getValue()));
    }

    /**
     * Sets the result of the correlation or variance-covariance matrix algorithm in the batch processing mode
     * @param id   Identifier of the result, @ref ResultId
     * @param val Object to store the result
     */
    public void set(ResultId id, NumericTable val) {
        if (id != ResultId.covariance && id != ResultId.correlation && id != ResultId.mean) {
            throw new IllegalArgumentException("id unsupported");
        }
        cSetResultTable(cObject, id.getValue(), val.getCObject());
    }

    private native long cNewResult();

    private native long cGetResultTable(long cObject, int id);

    private native void cSetResultTable(long cResult, int id, long cNumericTable);
}
/** @} */
