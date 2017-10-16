/* file: kdtree_knn_classification_predict_dense_default_distributed.h */
/*******************************************************************************
* Copyright 2014-2017 Intel Corporation
* All Rights Reserved.
*
* If this  software was obtained  under the  Intel Simplified  Software License,
* the following terms apply:
*
* The source code,  information  and material  ("Material") contained  herein is
* owned by Intel Corporation or its  suppliers or licensors,  and  title to such
* Material remains with Intel  Corporation or its  suppliers or  licensors.  The
* Material  contains  proprietary  information  of  Intel or  its suppliers  and
* licensors.  The Material is protected by  worldwide copyright  laws and treaty
* provisions.  No part  of  the  Material   may  be  used,  copied,  reproduced,
* modified, published,  uploaded, posted, transmitted,  distributed or disclosed
* in any way without Intel's prior express written permission.  No license under
* any patent,  copyright or other  intellectual property rights  in the Material
* is granted to  or  conferred  upon  you,  either   expressly,  by implication,
* inducement,  estoppel  or  otherwise.  Any  license   under such  intellectual
* property rights must be express and approved by Intel in writing.
*
* Unless otherwise agreed by Intel in writing,  you may not remove or alter this
* notice or  any  other  notice   embedded  in  Materials  by  Intel  or Intel's
* suppliers or licensors in any way.
*
*
* If this  software  was obtained  under the  Apache License,  Version  2.0 (the
* "License"), the following terms apply:
*
* You may  not use this  file except  in compliance  with  the License.  You may
* obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
*
*
* Unless  required  by   applicable  law  or  agreed  to  in  writing,  software
* distributed under the License  is distributed  on an  "AS IS"  BASIS,  WITHOUT
* WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
*
* See the   License  for the   specific  language   governing   permissions  and
* limitations under the License.
*******************************************************************************/

/*
//++
//  Declaration of template function that computes K-Nearest Neighbors prediction results.
//--
*/

#ifndef __KDTREE_KNN_CLASSIFICATION_PREDICT_DENSE_DEFAULT_DISTRIBUTED_H__
#define __KDTREE_KNN_CLASSIFICATION_PREDICT_DENSE_DEFAULT_DISTRIBUTED_H__

#include "kdtree_knn_classification_predict.h"
#include "kdtree_knn_classification_model_impl.h"
#include "service_memory.h"
#include "kernel.h"
#include "numeric_table.h"
#include "service_blas.h"

namespace daal
{
namespace algorithms
{
namespace kdtree_knn_classification
{
namespace internal
{

template <typename T, CpuType cpu> class Stack;
template <typename T, CpuType cpu> class Heap;

} // namespace internal

namespace prediction
{
namespace internal
{

using namespace daal::data_management;
using namespace daal::algorithms::kdtree_knn_classification::internal;

template <typename algorithmFPType, CpuType cpu> class ColumnsArray;
template <typename algorithmFPType, CpuType cpu> struct GlobalNeighbors;
template <typename algorithmFPType> struct SearchNode;

template <typename algorithmFPType, prediction::Method method, CpuType cpu>
class KNNClassificationPredictDistrStep1Kernel : public daal::algorithms::Kernel
{};

template <typename algorithmFPType, CpuType cpu>
class KNNClassificationPredictDistrStep1Kernel<algorithmFPType, defaultDense, cpu> : public daal::algorithms::Kernel
{
public:
    services::Status compute(const NumericTable * x, const PartialModel * m, NumericTable * keys,
                             const daal::algorithms::Parameter * par);
};

template <typename algorithmFPType, prediction::Method method, CpuType cpu>
class KNNClassificationPredictDistrStep2Kernel : public daal::algorithms::Kernel
{};

template <typename algorithmFPType, CpuType cpu>
class KNNClassificationPredictDistrStep2Kernel<algorithmFPType, defaultDense, cpu> : public daal::algorithms::Kernel
{
public:
    services::Status compute(const NumericTable * x, const NumericTable * interm, int key, int round, const PartialModel * m,
                             size_t respCount, const NumericTable * const * resp, const size_t * respIDs,
                             size_t inputQueryCount, const NumericTable * const * commInputQueries, const size_t * commInputQueriesNodeIDs,
                             size_t outputResponsesCount, NumericTable * const * commOutputResponses, const size_t * commOutputResponsesNodeIDs,
                             size_t queryCount, NumericTable * const * queries, const size_t * queryIDs,
                             NumericTable * y, const daal::algorithms::Parameter * par);

protected:
    void findNearestNeighbors(const algorithmFPType * query, Heap<GlobalNeighbors<algorithmFPType, cpu>, cpu> & heap,
                              Stack<SearchNode<algorithmFPType>, cpu> & stack, size_t k, algorithmFPType radius,
                              const KDTreeTable & kdTreeTable, size_t rootTreeNodeIndex,
                              const ColumnsArray<algorithmFPType, cpu> &data);

    size_t findList(const algorithmFPType * query, size_t featureCount, algorithmFPType radius, size_t * list,
                    size_t listCapacity, size_t key, Stack<SearchNode<algorithmFPType>, cpu> & stack,
                    const PartitioningKDTreeTable & kdTreeTable);

    services::Status predict(algorithmFPType & predictedClass, const Heap<GlobalNeighbors<algorithmFPType, cpu>, cpu> & heap, size_t k);
};

} // namespace internal
} // namespace prediction
} // namespace kdtree_knn_classification
} // namespace algorithms
} // namespace daal

#endif