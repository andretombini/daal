/* file: AveragePooling2dForwardResult.java */
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
 * @ingroup average_pooling2d_forward
 * @{
 */
package com.intel.daal.algorithms.neural_networks.layers.average_pooling2d;

import com.intel.daal.data_management.data.Factory;
import com.intel.daal.data_management.data.NumericTable;
import com.intel.daal.services.DaalContext;

/**
 * <a name="DAAL-CLASS-ALGORITHMS__NEURAL_NETWORKS__LAYERS__AVERAGE_POOLING2D__AVERAGEPOOLING2DFORWARDRESULT"></a>
 * @brief Class that provides methods to access the result obtained with the compute() method
 *        of the forward two-dimensional average pooling layer
 */
public final class AveragePooling2dForwardResult extends com.intel.daal.algorithms.neural_networks.layers.pooling2d.Pooling2dForwardResult {
    /** @private */
    static {
        System.loadLibrary("JavaAPI");
    }

    public AveragePooling2dForwardResult(DaalContext context, long cObject) {
        super(context, cObject);
    }

    /**
    * Constructs the forward two-dimensional average pooling layer
    * @param context   Context to manage the forward two-dimensional average pooling layer
    */
    public AveragePooling2dForwardResult(DaalContext context) {
        super(context);
        this.cObject = cNewResult();
    }

    /**
     * Returns the result of the forward two-dimensional average pooling layer
     * @param  id   Identifier of the result
     * @return Result that corresponds to the given identifier
     */
    public NumericTable get(AveragePooling2dLayerDataId id) {
        if (id == AveragePooling2dLayerDataId.auxInputDimensions) {
            return (NumericTable)Factory.instance().createObject(getContext(), cGetValue(cObject, id.getValue()));
        }
        else {
            throw new IllegalArgumentException("id unsupported");
        }
    }

    /**
     * Sets the result of the forward two-dimensional average pooling layer
     * @param id   Identifier of the result
     * @param val  Result that corresponds to the given identifier
     */
    public void set(AveragePooling2dLayerDataId id, NumericTable val) {
        if (id == AveragePooling2dLayerDataId.auxInputDimensions) {
            cSetValue(cObject, id.getValue(), val.getCObject());
        }
        else {
            throw new IllegalArgumentException("id unsupported");
        }
    }

    private native long cNewResult();
    private native long cGetValue(long cObject, int id);
    private native void cSetValue(long cObject, int id, long ntAddr);
}
/** @} */
