import java.io.Serializable;
import java.util.function.Function;

interface SerializableLambda extends Function<Object, Object>, Serializable {}
