/* file: Pooling1dForwardInput.java */
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
 * @defgroup pooling1d_forward Forward One-dimensional Pooling Layer
 * @brief Contains classes for the forward one-dimensional (1D) pooling layer
 * @ingroup pooling1d
 * @{
 */
package com.intel.daal.algorithms.neural_networks.layers.pooling1d;

import com.intel.daal.services.DaalContext;

/**
 * <a name="DAAL-CLASS-ALGORITHMS__NEURAL_NETWORKS__LAYERS__POOLING1D__POOLING1DFORWARDINPUT"></a>
 * @brief %Input object for the forward one-dimensional pooling layer
 */
public class Pooling1dForwardInput extends com.intel.daal.algorithms.neural_networks.layers.ForwardInput {
    /** @private */
    static {
        System.loadLibrary("JavaAPI");
    }

    public Pooling1dForwardInput(DaalContext context, long cObject) {
        super(context, cObject);
    }
}
/** @} */
