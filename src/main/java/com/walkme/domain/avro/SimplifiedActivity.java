/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.walkme.domain.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class SimplifiedActivity extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 7304977585261353493L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"SimplifiedActivity\",\"namespace\":\"com.walkme.domain.avro\",\"fields\":[{\"name\":\"activityType\",\"type\":\"string\"},{\"name\":\"startTimestamp\",\"type\":\"long\"},{\"name\":\"endTimestamp\",\"type\":[\"null\",\"long\"],\"default\":null}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<SimplifiedActivity> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<SimplifiedActivity> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<SimplifiedActivity> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<SimplifiedActivity> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<SimplifiedActivity> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this SimplifiedActivity to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a SimplifiedActivity from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a SimplifiedActivity instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static SimplifiedActivity fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private java.lang.CharSequence activityType;
  private long startTimestamp;
  private java.lang.Long endTimestamp;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public SimplifiedActivity() {}

  /**
   * All-args constructor.
   * @param activityType The new value for activityType
   * @param startTimestamp The new value for startTimestamp
   * @param endTimestamp The new value for endTimestamp
   */
  public SimplifiedActivity(java.lang.CharSequence activityType, java.lang.Long startTimestamp, java.lang.Long endTimestamp) {
    this.activityType = activityType;
    this.startTimestamp = startTimestamp;
    this.endTimestamp = endTimestamp;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return activityType;
    case 1: return startTimestamp;
    case 2: return endTimestamp;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: activityType = (java.lang.CharSequence)value$; break;
    case 1: startTimestamp = (java.lang.Long)value$; break;
    case 2: endTimestamp = (java.lang.Long)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'activityType' field.
   * @return The value of the 'activityType' field.
   */
  public java.lang.CharSequence getActivityType() {
    return activityType;
  }


  /**
   * Sets the value of the 'activityType' field.
   * @param value the value to set.
   */
  public void setActivityType(java.lang.CharSequence value) {
    this.activityType = value;
  }

  /**
   * Gets the value of the 'startTimestamp' field.
   * @return The value of the 'startTimestamp' field.
   */
  public long getStartTimestamp() {
    return startTimestamp;
  }


  /**
   * Sets the value of the 'startTimestamp' field.
   * @param value the value to set.
   */
  public void setStartTimestamp(long value) {
    this.startTimestamp = value;
  }

  /**
   * Gets the value of the 'endTimestamp' field.
   * @return The value of the 'endTimestamp' field.
   */
  public java.lang.Long getEndTimestamp() {
    return endTimestamp;
  }


  /**
   * Sets the value of the 'endTimestamp' field.
   * @param value the value to set.
   */
  public void setEndTimestamp(java.lang.Long value) {
    this.endTimestamp = value;
  }

  /**
   * Creates a new SimplifiedActivity RecordBuilder.
   * @return A new SimplifiedActivity RecordBuilder
   */
  public static com.walkme.domain.avro.SimplifiedActivity.Builder newBuilder() {
    return new com.walkme.domain.avro.SimplifiedActivity.Builder();
  }

  /**
   * Creates a new SimplifiedActivity RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new SimplifiedActivity RecordBuilder
   */
  public static com.walkme.domain.avro.SimplifiedActivity.Builder newBuilder(com.walkme.domain.avro.SimplifiedActivity.Builder other) {
    if (other == null) {
      return new com.walkme.domain.avro.SimplifiedActivity.Builder();
    } else {
      return new com.walkme.domain.avro.SimplifiedActivity.Builder(other);
    }
  }

  /**
   * Creates a new SimplifiedActivity RecordBuilder by copying an existing SimplifiedActivity instance.
   * @param other The existing instance to copy.
   * @return A new SimplifiedActivity RecordBuilder
   */
  public static com.walkme.domain.avro.SimplifiedActivity.Builder newBuilder(com.walkme.domain.avro.SimplifiedActivity other) {
    if (other == null) {
      return new com.walkme.domain.avro.SimplifiedActivity.Builder();
    } else {
      return new com.walkme.domain.avro.SimplifiedActivity.Builder(other);
    }
  }

  /**
   * RecordBuilder for SimplifiedActivity instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<SimplifiedActivity>
    implements org.apache.avro.data.RecordBuilder<SimplifiedActivity> {

    private java.lang.CharSequence activityType;
    private long startTimestamp;
    private java.lang.Long endTimestamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.walkme.domain.avro.SimplifiedActivity.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.activityType)) {
        this.activityType = data().deepCopy(fields()[0].schema(), other.activityType);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.startTimestamp)) {
        this.startTimestamp = data().deepCopy(fields()[1].schema(), other.startTimestamp);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.endTimestamp)) {
        this.endTimestamp = data().deepCopy(fields()[2].schema(), other.endTimestamp);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
    }

    /**
     * Creates a Builder by copying an existing SimplifiedActivity instance
     * @param other The existing instance to copy.
     */
    private Builder(com.walkme.domain.avro.SimplifiedActivity other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.activityType)) {
        this.activityType = data().deepCopy(fields()[0].schema(), other.activityType);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.startTimestamp)) {
        this.startTimestamp = data().deepCopy(fields()[1].schema(), other.startTimestamp);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.endTimestamp)) {
        this.endTimestamp = data().deepCopy(fields()[2].schema(), other.endTimestamp);
        fieldSetFlags()[2] = true;
      }
    }

    /**
      * Gets the value of the 'activityType' field.
      * @return The value.
      */
    public java.lang.CharSequence getActivityType() {
      return activityType;
    }


    /**
      * Sets the value of the 'activityType' field.
      * @param value The value of 'activityType'.
      * @return This builder.
      */
    public com.walkme.domain.avro.SimplifiedActivity.Builder setActivityType(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.activityType = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'activityType' field has been set.
      * @return True if the 'activityType' field has been set, false otherwise.
      */
    public boolean hasActivityType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'activityType' field.
      * @return This builder.
      */
    public com.walkme.domain.avro.SimplifiedActivity.Builder clearActivityType() {
      activityType = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'startTimestamp' field.
      * @return The value.
      */
    public long getStartTimestamp() {
      return startTimestamp;
    }


    /**
      * Sets the value of the 'startTimestamp' field.
      * @param value The value of 'startTimestamp'.
      * @return This builder.
      */
    public com.walkme.domain.avro.SimplifiedActivity.Builder setStartTimestamp(long value) {
      validate(fields()[1], value);
      this.startTimestamp = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'startTimestamp' field has been set.
      * @return True if the 'startTimestamp' field has been set, false otherwise.
      */
    public boolean hasStartTimestamp() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'startTimestamp' field.
      * @return This builder.
      */
    public com.walkme.domain.avro.SimplifiedActivity.Builder clearStartTimestamp() {
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'endTimestamp' field.
      * @return The value.
      */
    public java.lang.Long getEndTimestamp() {
      return endTimestamp;
    }


    /**
      * Sets the value of the 'endTimestamp' field.
      * @param value The value of 'endTimestamp'.
      * @return This builder.
      */
    public com.walkme.domain.avro.SimplifiedActivity.Builder setEndTimestamp(java.lang.Long value) {
      validate(fields()[2], value);
      this.endTimestamp = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'endTimestamp' field has been set.
      * @return True if the 'endTimestamp' field has been set, false otherwise.
      */
    public boolean hasEndTimestamp() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'endTimestamp' field.
      * @return This builder.
      */
    public com.walkme.domain.avro.SimplifiedActivity.Builder clearEndTimestamp() {
      endTimestamp = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public SimplifiedActivity build() {
      try {
        SimplifiedActivity record = new SimplifiedActivity();
        record.activityType = fieldSetFlags()[0] ? this.activityType : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.startTimestamp = fieldSetFlags()[1] ? this.startTimestamp : (java.lang.Long) defaultValue(fields()[1]);
        record.endTimestamp = fieldSetFlags()[2] ? this.endTimestamp : (java.lang.Long) defaultValue(fields()[2]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<SimplifiedActivity>
    WRITER$ = (org.apache.avro.io.DatumWriter<SimplifiedActivity>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<SimplifiedActivity>
    READER$ = (org.apache.avro.io.DatumReader<SimplifiedActivity>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeString(this.activityType);

    out.writeLong(this.startTimestamp);

    if (this.endTimestamp == null) {
      out.writeIndex(0);
      out.writeNull();
    } else {
      out.writeIndex(1);
      out.writeLong(this.endTimestamp);
    }

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.activityType = in.readString(this.activityType instanceof Utf8 ? (Utf8)this.activityType : null);

      this.startTimestamp = in.readLong();

      if (in.readIndex() != 1) {
        in.readNull();
        this.endTimestamp = null;
      } else {
        this.endTimestamp = in.readLong();
      }

    } else {
      for (int i = 0; i < 3; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.activityType = in.readString(this.activityType instanceof Utf8 ? (Utf8)this.activityType : null);
          break;

        case 1:
          this.startTimestamp = in.readLong();
          break;

        case 2:
          if (in.readIndex() != 1) {
            in.readNull();
            this.endTimestamp = null;
          } else {
            this.endTimestamp = in.readLong();
          }
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}










