package net.hardlight.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import net.hardlight.RenamePair;

import java.lang.reflect.Type;

public class RenamePairSerializer implements JsonSerializer<RenamePair>
{
    /**
     * Gson invokes this call-back method during serialization when it encounters a field of the
     * specified type.
     * <p/>
     * <p>In the implementation of this call-back method, you should consider invoking
     * {@link com.google.gson.JsonSerializationContext#serialize(Object, java.lang.reflect.Type)} method to create
     * JsonElements for any
     * non-trivial field of the {@code src} object. However, you should never invoke it on the
     * {@code src} object itself since that will cause an infinite loop (Gson will call your
     * call-back method again).</p>
     *
     * @param src       the object that needs to be converted to Json.
     * @param typeOfSrc the actual type (fully genericized version) of the source object.
     * @param context
     * @return a JsonElement corresponding to the specified object.
     */
    @Override
    public JsonElement serialize(RenamePair src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject obj = new JsonObject();

        obj.add("from", context.serialize(src.getFrom()));
        obj.add("to", context.serialize(src.getTo()));

        return obj;
    }
}
