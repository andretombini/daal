/* file: average_pooling3d_layer_types.h */
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

/*
//++
//  Implementation of three-dimensional average pooling layer.
//--
*/

#ifndef __AVERAGE_POOLING3D_LAYER_TYPES_H__
#define __AVERAGE_POOLING3D_LAYER_TYPES_H__

#include "algorithms/algorithm.h"
#include "data_management/data/tensor.h"
#include "data_management/data/homogen_tensor.h"
#include "services/daal_defines.h"
#include "algorithms/neural_networks/layers/pooling3d/pooling3d_layer_types.h"

namespace daal
{
namespace algorithms
{
namespace neural_networks
{
namespace layers
{
/**
 * @defgroup average_pooling3d Three-dimensional Average Pooling Layer
 * \copydoc daal::algorithms::neural_networks::layers::average_pooling3d
 * @ingroup pooling3d
 * @{
 */
namespace average_pooling3d
{
/**
 * <a name="DAAL-ENUM-ALGORITHMS__NEURAL_NETWORKS__LAYERS__AVERAGE_POOLING3D__METHOD"></a>
 * \brief Computation methods for the average 3D pooling layer
 */
enum Method
{
    defaultDense = 0  /*!<  Default: performance-oriented method */
};

/**
 * \brief Identifiers of input objects for the backward average 3D pooling layer
 *        and results for the forward average 3D pooling layer
 */
enum LayerDataId
{
    auxInputDimensions,  /*!< Numeric table that stores forward average pooling layer results */
    lastLayerDataId = auxInputDimensions
};

/**
 * \brief Contains version 1.0 of Intel(R) Data Analytics Acceleration Library (Intel(R) DAAL) interface.
 */
namespace interface1
{
/**
 * <a name="DAAL-STRUCT-ALGORITHMS__NEURAL_NETWORKS__LAYERS__AVERAGE_POOLING3D__PARAMETER"></a>
 * \brief Parameters for the average 3D pooling layer
 *
 * \snippet neural_networks/layers/pooling3d/average_pooling3d_layer_types.h Parameter source code
 */
/* [Parameter source code] */
struct DAAL_EXPORT Parameter: public layers::pooling3d::Parameter
{
    /**
     * Constructs the parameters of 3D pooling layer
     * \param[in] firstIndex        Index of the first of three dimensions on which the pooling is performed
     * \param[in] secondIndex       Index of the second of three dimensions on which the pooling is performed
     * \param[in] thirdIndex        Index of the third of three dimensions on which the pooling is performed
     * \param[in] firstKernelSize   Size of the first dimension of 3D subtensor for which the average element is computed
     * \param[in] secondKernelSize  Size of the second dimension of 3D subtensor for which the average element is computed
     * \param[in] thirdKernelSize   Size of the third dimension of 3D subtensor for which the average element is computed
     * \param[in] firstStride       Interval over the first dimension on which the pooling is performed
     * \param[in] secondStride      Interval over the second dimension on which the pooling is performed
     * \param[in] thirdStride       Interval over the third dimension on which the pooling is performed
     * \param[in] firstPadding      Number of data elements to implicitly add to the the first dimension
     *                              of the 3D subtensor on which the pooling is performed
     * \param[in] secondPadding     Number of data elements to implicitly add to the the second dimension
     *                              of the 3D subtensor on which the pooling is performed
     * \param[in] thirdPadding      Number of data elements to implicitly add to the the third dimension
     *                              of the 3D subtensor on which the pooling is performed
     */
    Parameter(size_t firstIndex, size_t secondIndex, size_t thirdIndex,
              size_t firstKernelSize = 2, size_t secondKernelSize = 2, size_t thirdKernelSize = 2,
              size_t firstStride = 2, size_t secondStride = 2, size_t thirdStride = 2,
              size_t firstPadding = 0, size_t secondPadding = 0, size_t thirdPadding = 0);
};
/* [Parameter source code] */

} // interface1
using interface1::Parameter;

} // namespace average_pooling3d
/** @} */
} // namespace layers
} // namespace neural_networks
} // namespace algorithm
} // namespace daal

#endif
