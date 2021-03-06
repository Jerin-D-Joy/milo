/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class UserIdentityToken implements UaStructure {

    public static final NodeId TypeId = Identifiers.UserIdentityToken;
    public static final NodeId BinaryEncodingId = Identifiers.UserIdentityToken_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UserIdentityToken_Encoding_DefaultXml;

    protected final String policyId;

    public UserIdentityToken() {
        this.policyId = null;
    }

    public UserIdentityToken(String policyId) {
        this.policyId = policyId;
    }

    public String getPolicyId() { return policyId; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("PolicyId", policyId)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<UserIdentityToken> {

        @Override
        public Class<UserIdentityToken> getType() {
            return UserIdentityToken.class;
        }

        @Override
        public UserIdentityToken decode(UaDecoder decoder) throws UaSerializationException {
            String policyId = decoder.readString("PolicyId");

            return new UserIdentityToken(policyId);
        }

        @Override
        public void encode(UserIdentityToken value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("PolicyId", value.policyId);
        }
    }

}
