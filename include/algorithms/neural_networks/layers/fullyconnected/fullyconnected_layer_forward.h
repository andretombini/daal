/* file: fullyconnected_layer_forward.h */
/*******************************************************************************
* Copyright 2014-2016 Intel Corporation
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*******************************************************************************/

/*
//++
//  Implementation of forward fully-connected layer.
//--
*/

#ifndef __NEURAL_NETWORK_FULLYCONNECTED_LAYER_FORWARD_H__
#define __NEURAL_NETWORK_FULLYCONNECTED_LAYER_FORWARD_H__

#include "algorithms/algorithm.h"
#include "data_management/data/tensor.h"
#include "services/daal_defines.h"
#include "algorithms/neural_networks/layers/layer.h"
#include "algorithms/neural_networks/layers/fullyconnected/fullyconnected_layer_types.h"
#include "algorithms/neural_networks/layers/fullyconnected/fullyconnected_layer_forward_types.h"

namespace daal
{
namespace algorithms
{
namespace neural_networks
{
namespace layers
{
namespace fullyconnected
{
namespace forward
{
namespace interface1
{
/**
 * \brief Provides methods to run implementations of the forward fully-connected layer.
 *        This class is associated with the daal::algorithms::neural_networks::layers::fullyconnected::forward::Batch class
 *        and supports the method of forward fully-connected layer computation in the batch processing mode
 *
 * \tparam algorithmFPType  Data type to use in intermediate computations of forward fully-connected layer, double or float
 * \tparam method           Computation method of the layer, \ref daal::algorithms::neural_networks::layers::fullyconnected::Method
 * \tparam cpu              Version of the cpu-specific implementation of the layer, \ref daal::CpuType
 */
template<typename algorithmFPType, Method method, CpuType cpu>
class DAAL_EXPORT BatchContainer : public AnalysisContainerIface<batch>
{
public:
    /**
     * Constructs the container for the forward fully-connected layer with the specified environment
     * \param[in] daalEnv   Environment object
     */
    BatchContainer(daal::services::Environment::env *daalEnv);
    ~BatchContainer();

    void compute();
};

/**
 * <a name="DAAL-CLASS-ALGORITHMS__NEURAL_NETWORKS__LAYERS__FULLYCONNECTED__FORWARD__BATCH"></a>
 * \brief Provides methods for forward fully-connected layer computations in the batch processing mode
 *
 * \tparam algorithmFPType  Data type to use in intermediate computations of forward fully-connected layer, double or float
 * \tparam method           Computation method of the layer, \ref Method
 *
 * \par Enumerations
 *      - \ref Method          Computation methods for the forward fully-connected layer
 *      - \ref LayerDataId Identifiers of results of the forward fully-connected layer
 *
 * \par References
 *      - Parameter class
 *      - Input class
 *      - Result class
 *      - <a href="DAAL-REF-FULLYCONNECTEDFORWARD-ALGORITHM">Forward fully-connected layer description and usage models</a>
 *      - backward::Batch class
 */
template<typename algorithmFPType = float, Method method = defaultDense>
class Batch : public layers::forward::LayerIface
{
public:
    Parameter *parameter; /*!< %Parameters of the layer */
    Input input;          /*!< %Input objects of the layer */

    /**
     * Constructs forward fully-connected layer
     *  \param[in] nOutputs A number of layer outputs
     *  \param[in] dim      Dimension for which forward propagation step of the fully-connected layer is performed
     */
    Batch(const size_t nOutputs, size_t dim = 0) : parameter(new Parameter(nOutputs, dim))
    {
        initialize();
    };

    /**
     * Constructs fully-connected forward layer by copying input objects and parameters of another forward fully-connected layer
     * \param[in] other A layer to be used as the source to initialize the input objects
     *                  and parameters of this layer
     */
    Batch(const Batch<algorithmFPType, method> &other): parameter(other.parameter)
    {
        initialize();
        input.set(layers::forward::data, other.input.get(layers::forward::data));
        input.set(layers::forward::weights, other.input.get(layers::forward::weights));
        input.set(layers::forward::biases, other.input.get(layers::forward::biases));
    }

    /**
    * Returns method of the layer
    * \return Method of the layer
    */
    virtual int getMethod() const DAAL_C11_OVERRIDE { return(int) method; }

    /**
     * Returns the structure that contains the input objects of the forward fully-connected layer
     * \return Structure that contains the input objects of the forward fully-connected layer
     */
    virtual Input *getLayerInput() DAAL_C11_OVERRIDE { return &input; }

    /**
     * Returns the structure that contains parameters of the forward fully-connected layer
     * \return Structure that contains parameters of the forward fully-connected layer
     */
    virtual Parameter *getLayerParameter() { return parameter; };

    /**
     * Returns the structure that contains results of fully-connected layer
     * \return Structure that contains results of fully-connected layer
     */
    services::SharedPtr<layers::forward::Result> getLayerResult() DAAL_C11_OVERRIDE
    {
        return getResult();
    }

    /**
     * Returns the structure that contains results of fully-connected layer
     * \return Structure that contains results of fully-connected layer
     */
    services::SharedPtr<Result> getResult()
    {
        return _result;
    }

    /**
     * Registers user-allocated memory to store results of fully-connected layer
     * \param[in] result  Structure to store results of fully-connected layer
     */
    void setResult(services::SharedPtr<Result> result)
    {
        _result = result;
        _res = _result.get();
    }

    /**
     * Returns a pointer to the newly allocated fully-connected layer
     * with a copy of input objects and parameters of this fully-connected layer
     * \return Pointer to the newly allocated layer
     */
    services::SharedPtr<Batch<algorithmFPType, method> > clone() const
    {
        return services::SharedPtr<Batch<algorithmFPType, method> >(cloneImpl());
    }

    /**
    * Allocates memory to store the result of the forward fully-connected layer
    */
    virtual void allocateResult() DAAL_C11_OVERRIDE
    {
        this->_result->template allocate<algorithmFPType>(&(this->input), parameter, (int) method);
        this->_res = this->_result.get();
    }

    /**
     * Allocates memory buffers needed for the computations
     */
    virtual void allocateLayerData() DAAL_C11_OVERRIDE
    {
        this->_result->template allocateLayerData<algorithmFPType>(&(this->input), parameter, (int) method);
        this->_res = this->_result.get();
    }

protected:
    virtual Batch<algorithmFPType, method> *cloneImpl() const DAAL_C11_OVERRIDE
    {
        return new Batch<algorithmFPType, method>(*this);
    }

    void setParameter() DAAL_C11_OVERRIDE { _par = parameter; };

    virtual void allocateInput() DAAL_C11_OVERRIDE
    {
        this->input.template allocate<algorithmFPType>(parameter, (int) method);
    }

    void initialize()
    {
        Analysis<batch>::_ac = new __DAAL_ALGORITHM_CONTAINER(batch, BatchContainer, algorithmFPType, method)(&_env);
        _in = &input;
        _par = parameter;
        _result = services::SharedPtr<Result>(new Result());
    }

private:
    services::SharedPtr<Result> _result;
};

} // namespace interface1
using interface1::BatchContainer;
using interface1::Batch;
} // namespace forward
} // namespace fullyconnected
} // namespace layers
} // namespace neural_networks
} // namespace algorithms
} // namespace daal
#endif