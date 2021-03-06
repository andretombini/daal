/* file: SerializableBase.java */
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
 * @defgroup serialization Data Serialization and Deserialization
 * @brief Contains classes that implement serialization and deserialization.
 * @ingroup data_management
 * @{
 */
package com.intel.daal.data_management.data;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;

import com.intel.daal.services.ContextClient;
import com.intel.daal.services.DaalContext;

/**
*  <a name="DAAL-CLASS-DATA_MANAGEMENT__DATA__SERIALIZABLEBASE"></a>
*  @brief Class that provides methods for serialization and deserialization
*/
public class SerializableBase extends ContextClient
        implements Serializable, com.intel.daal.services.Disposable {

    /** @private */
    static {
        System.loadLibrary("JavaAPI");
    }

    /**
     * Constructs the serializable object
     * @param context   Context to manage the serializable object
     */
    public SerializableBase(DaalContext context) {
        super(context);
        this.cObject = 0;
        this.serializedCObject = null;
    }

    public SerializableBase(DaalContext context, long cObject) {
        super(context);
        this.cObject = cObject;
        this.serializedCObject = null;
    }

    /**
     * Checks if the native object is deserialized
     */
    public void checkCObject() {
        if (this.cObject == 0 && this.serializedCObject != null) {
            throwUnpacked();
        }
    }

    /**
     * Returns the address of the native object
     *
     * @return Address of the native object
     */
    public long getCObject() {
        checkCObject();
        return this.cObject;
    }

    /**
     * Serializes an object
     */
    public void pack() {
        synchronized(this) {
            DaalContext myContext = getContext();
            if (myContext != null) {
                changeContext(null);
            }
            onPack();
            if (this.cObject != 0) {
                serializeCObject();
            }
        }
    }

    /**
     * Deserializes an object
     * @param context   Context to manage a deserialized object
     */
    public void unpack(DaalContext context) {
        synchronized(this) {
            cSetJavaVM();
            changeContext(context);
            if (this.cObject == 0) {
                cSetDaalContext(context);
                onUnpack(context);
                cClearDaalContext();
            }
            this.serializedCObject = null;
        }
    }

    /**
     * Serializes an object from a native method
     */
    void packNative() {
        synchronized(this) {
            DaalContext myContext = getContext();
            if (myContext != null) {
                changeContext(null);
            }
            onPack();
            dispose();
        }
    }

    /**
     * Deserializes an object from a native method
     *
     * @param context   Context to manage a deserialized object
     * @param cObject   Object that called this method
     */
    void unpackNative(DaalContext context, long cObject) {
        synchronized(this) {
            cSetJavaVM();
            changeContext(context);
            if (this.cObject == 0) {
                cSetDaalContext(context);
                this.cObject = cObject;
                onUnpack(context);
                cClearDaalContext();
            }
            this.serializedCObject = null;
        }
    }

    /**
     * Releases the memory allocated for the native object
     */
    @Override
    public void dispose() {
        if (this.cObject != 0) {
            cDispose(this.cObject);
            this.cObject = 0;
        }
    }

    /* --------------------- */
    /* Private and Protected */
    /* --------------------- */

    /* Pointer to SharedPtr<> for the C object */
    protected transient long cObject;

    /* Serialized C object */
    protected byte[][] serializedCObject;

    protected boolean onSerializeCObject() {
        return true;
    }

    protected void serializeCObject() {
        if (onSerializeCObject() && this.cObject != 0) {
            serializedCObject = cSerializeCObject(this.cObject);
            dispose();
        }
    }

    protected void onPack() {}

    protected void onUnpack(DaalContext context) {
        deserializeCObject();
    }

    protected void deserializeCObject() {
        if (this.serializedCObject != null) {
            if (this.cObject != 0) {
                this.dispose();
            }
            this.cObject = cDeserializeCObject(serializedCObject);
        }
    }

    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        aInputStream.defaultReadObject();
        this.cObject = 0;
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.defaultWriteObject();
    }

    private native byte[][] cSerializeCObject(long cDataCollection);

    private native long cDeserializeCObject(byte[][] byteArray);

    private native void cFreeByteBuffer(ByteBuffer buffer);

    private native void cDispose(long parAddr);

    private native void throwUnpacked();

    private native void cSetJavaVM();

    private native void cSetDaalContext(DaalContext context);

    private native void cClearDaalContext();
}
/** @} */
