package dev.mayuna.nrdyloader;

import lombok.NonNull;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

/**
 * Main class for interacting with the nrdy loader<br>
 * <strong>Serialized objects must have public no-args constructor</strong>
 */
public final class Nrdy {

    // TODO: NrdyConvertor for custom serialization

    /**
     * Converts an object to a nrdy string
     *
     * @param object Non-null object to convert
     *
     * @return The nrdy string
     */
    public String convertObjectToNrdyString(@NotNull @NonNull Object object) {
        final StringBuilder nrdyBuilder = new StringBuilder();
        final Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (Modifier.isTransient(field.getModifiers())) {
                continue; // Skip transient fields
            }

            boolean wasAccessible = field.isAccessible();
            field.setAccessible(true);

            try {
                final Object fieldValue = field.get(object);

                System.out.println("Object: " + field.getName() + " - " + field.getType().getName() + " (" + fieldValue + ")");

                if (fieldValue == null) {
                    nrdyBuilder.append("null;");
                    continue;
                }

                if (field.getType().isPrimitive()) {
                    nrdyBuilder.append(fieldValue).append(";");
                    continue;
                }

                // String
                if (field.getType().isAssignableFrom(String.class)) {
                    nrdyBuilder.append(fieldValue).append(";");
                    continue;
                }

                // Integer
                if (field.getType().isAssignableFrom(Integer.class)) {
                    nrdyBuilder.append(fieldValue).append(";");
                    continue;
                }

                // Boolean
                if (field.getType().isAssignableFrom(Boolean.class)) {
                    nrdyBuilder.append(fieldValue).append(";");
                    continue;
                }

                // Double
                if (field.getType().isAssignableFrom(Double.class)) {
                    nrdyBuilder.append(fieldValue).append(";");
                    continue;
                }

                // Float
                if (field.getType().isAssignableFrom(Float.class)) {
                    nrdyBuilder.append(fieldValue).append(";");
                    continue;
                }

                // Long
                if (field.getType().isAssignableFrom(Long.class)) {
                    nrdyBuilder.append(fieldValue).append(";");
                    continue;
                }

                // Short
                if (field.getType().isAssignableFrom(Short.class)) {
                    nrdyBuilder.append(fieldValue).append(";");
                    continue;
                }

                // Byte
                if (field.getType().isAssignableFrom(Byte.class)) {
                    nrdyBuilder.append(fieldValue).append(";");
                    continue;
                }

                // Character
                if (field.getType().isAssignableFrom(Character.class)) {
                    nrdyBuilder.append(fieldValue).append(";");
                    continue;
                }

                // Enum
                if (field.getType().isEnum()) {
                    nrdyBuilder.append(((Enum<?>) fieldValue).name()).append(";");
                    continue;
                }

                if (field.getType().getName().equals("java.util.List")) {
                    throw new NrdyException("List serialization not implemented");
                }

                if (field.getType().getName().equals("java.util.Map")) {
                    throw new NrdyException("Map serialization not implemented");
                }

                if (field.getType().isArray()) {
                    throw new NrdyException("Array serialization not implemented");
                }

                /*
                // List
                if (field.getType().getName().equals("java.util.List")) {
                    System.out.println("List: " + field.getName() + " - " + fieldValue.getClass().getName() + " (" + fieldValue + ")");

                    nrdyBuilder.append("[");
                    for (Object item : (Collection<?>) fieldValue) {
                        convertArrayItem(nrdyBuilder, item);
                    }
                    nrdyBuilder.append("];");

                    continue;
                }

                if (field.getType().getName().equals("java.util.Map")) {
                    System.out.println("Map: " + field.getName() + " - " + fieldValue.getClass().getName() + " (" + fieldValue + ")");

                    nrdyBuilder.append("{");
                    for (Map.Entry<?, ?> entry : ((Map<?, ?>) fieldValue).entrySet()) {
                        nrdyBuilder.append(entry.getKey()).append(";");
                        convertArrayItem(nrdyBuilder, entry.getValue());
                    }
                    nrdyBuilder.append("};");

                    continue;
                }

                // Array
                if (field.getType().isArray()) {
                    int length = Array.getLength(fieldValue);
                    nrdyBuilder.append("[");

                    for (int i = 0; i < length; i++) {
                        Object item = Array.get(fieldValue, i);
                        convertArrayItem(nrdyBuilder, item);
                    }

                    nrdyBuilder.append("];");

                    continue;
                }*/

                // Serialize nested objects
                nrdyBuilder.append(convertObjectToNrdyString(fieldValue));
            } catch (IllegalAccessException exception) {
                throw new NrdyException("Failed to access field", exception);
            }

            if (!wasAccessible) {
                field.setAccessible(false);
            }
        }

        return nrdyBuilder.toString();
    }

    /**
     * Converts a nrdy string to an object
     *
     * @param nrdyString Non-null nrdy string
     * @param clazz      Non-null class of the object
     * @param <T>        The type of the object
     *
     * @return The object
     */
    public <T> T convertNrdyStringToObject(@NotNull @NonNull String nrdyString, @NotNull @NonNull Class<T> clazz) {
        T object;

        try {
            object = clazz.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException exception) {
            throw new NrdyException("Failed to instantiate object (missing public no-args constructor?)", exception);
        }

        final Field[] fields = clazz.getDeclaredFields();
        final String[] nrdyParts = nrdyString.split(";");
        int nrdyIndex = 0;

        for (Field field : fields) {
            if (Modifier.isTransient(field.getModifiers())) {
                continue; // Skip transient fields
            }

            boolean wasAccessible = field.isAccessible();
            field.setAccessible(true);

            try {
                final String nrdyPart = nrdyParts[nrdyIndex];
                nrdyIndex++;

                System.out.println("Object: " + field.getName() + " - " + field.getType().getName() + " (" + nrdyPart + ")");

                if (nrdyPart.equals("null")) {
                    field.set(object, null);
                    continue;
                }

                if (field.getType().isPrimitive()) {
                    if (field.getType().isAssignableFrom(int.class)) {
                        field.set(object, Integer.parseInt(nrdyPart));
                        continue;
                    }

                    if (field.getType().isAssignableFrom(boolean.class)) {
                        field.set(object, Boolean.parseBoolean(nrdyPart));
                        continue;
                    }

                    if (field.getType().isAssignableFrom(double.class)) {
                        field.set(object, Double.parseDouble(nrdyPart));
                        continue;
                    }

                    if (field.getType().isAssignableFrom(float.class)) {
                        field.set(object, Float.parseFloat(nrdyPart));
                        continue;
                    }

                    if (field.getType().isAssignableFrom(long.class)) {
                        field.set(object, Long.parseLong(nrdyPart));
                        continue;
                    }

                    if (field.getType().isAssignableFrom(short.class)) {
                        field.set(object, Short.parseShort(nrdyPart));
                        continue;
                    }

                    if (field.getType().isAssignableFrom(byte.class)) {
                        field.set(object, Byte.parseByte(nrdyPart));
                        continue;
                    }

                    if (field.getType().isAssignableFrom(char.class)) {
                        field.set(object, nrdyPart.charAt(0));
                        continue;
                    }
                }

                // String
                if (field.getType().isAssignableFrom(String.class)) {
                    field.set(object, nrdyPart);
                    continue;
                }

                // Integer
                if (field.getType().isAssignableFrom(Integer.class)) {
                    field.set(object, Integer.parseInt(nrdyPart));
                    continue;
                }

                // Boolean
                if (field.getType().isAssignableFrom(Boolean.class)) {
                    field.set(object, Boolean.parseBoolean(nrdyPart));
                    continue;
                }

                // Double
                if (field.getType().isAssignableFrom(Double.class)) {
                    field.set(object, Double.parseDouble(nrdyPart));
                    continue;
                }

                // Float
                if (field.getType().isAssignableFrom(Float.class)) {
                    field.set(object, Float.parseFloat(nrdyPart));
                    continue;
                }

                // Long
                if (field.getType().isAssignableFrom(Long.class)) {
                    field.set(object, Long.parseLong(nrdyPart));
                    continue;
                }

                // Short
                if (field.getType().isAssignableFrom(Short.class)) {
                    field.set(object, Short.parseShort(nrdyPart));
                    continue;
                }

                // Byte
                if (field.getType().isAssignableFrom(Byte.class)) {
                    field.set(object, Byte.parseByte(nrdyPart));
                    continue;
                }

                // Character
                if (field.getType().isAssignableFrom(Character.class)) {
                    field.set(object, nrdyPart.charAt(0));
                    continue;
                }

                // Enum
                if (field.getType().isEnum()) {
                    field.set(object, Enum.valueOf((Class<Enum>) field.getType(), nrdyPart));
                    continue;
                }

                // List
                /*
                if (field.getType().getName().equals("java.util.List")) {
                    System.out.println("List: " + field.getName() + " - " + field.getType().getName() + " (" + nrdyPart + ")");

                    nrdyIndex++; // Skip '['
                    Collection<String> list = (Collection<String>) field.get(object);

                    while (!nrdyParts[nrdyIndex].equals("]")) {
                        String item = nrdyParts[nrdyIndex];
                        nrdyIndex++;
                        list.add(item);
                    }

                    nrdyIndex++; // Skip ']'
                    continue;
                }*/

                // Map
                /*
                if (field.getType().getName().equals("java.util.Map")) {
                    System.out.println("Map: " + field.getName() + " - " + field.getType().getName() + " (" + nrdyPart + ")");

                    nrdyIndex++; // Skip '{'
                    Map<String, String> map = (Map<String, String>) field.get(object);

                    while (!nrdyParts[nrdyIndex].equals("}")) {
                        String key = nrdyParts[nrdyIndex];
                        nrdyIndex++;
                        String value = nrdyParts[nrdyIndex];
                        nrdyIndex++;
                        map.put(key, value);
                    }

                    nrdyIndex++; // Skip '}'
                }*/

                // Nested
                System.out.println("Nested: " + field.getName() + " - " + field.getType().getName() + " (" + nrdyPart + ")");
                field.set(object, convertNrdyStringToObject(nrdyPart, field.getType()));
            } catch (IllegalAccessException exception) {
                throw new NrdyException("Failed to access field", exception);
            }
        }

        return object;
    }

    /*
    private void convertArrayItem(StringBuilder nrdyBuilder, Object item) throws ClassNotFoundException {
        final Class<?> itemClass = Class.forName(item.getClass().getCanonicalName());

        System.out.println("ArrayItem: " + itemClass.getName() + " (" + item + ")");

        if (itemClass.isPrimitive()) {
            nrdyBuilder.append(item).append(";");
            return;
        }

        if (itemClass.isAssignableFrom(String.class)) {
            nrdyBuilder.append(item).append(";");
            return;
        }

        if (itemClass.isAssignableFrom(Integer.class)) {
            nrdyBuilder.append(item).append(";");
            return;
        }

        if (itemClass.isAssignableFrom(Boolean.class)) {
            nrdyBuilder.append(item).append(";");
            return;
        }

        if (itemClass.isAssignableFrom(Double.class)) {
            nrdyBuilder.append(item).append(";");
            return;
        }

        if (itemClass.isAssignableFrom(Float.class)) {
            nrdyBuilder.append(item).append(";");
            return;
        }

        if (itemClass.isAssignableFrom(Long.class)) {
            nrdyBuilder.append(item).append(";");
            return;
        }

        if (itemClass.isAssignableFrom(Short.class)) {
            nrdyBuilder.append(item).append(";");
            return;
        }

        if (itemClass.isAssignableFrom(Byte.class)) {
            nrdyBuilder.append(item).append(";");
            return;
        }

        if (itemClass.isAssignableFrom(Character.class)) {
            nrdyBuilder.append(item).append(";");
            return;
        }

        if (itemClass.isEnum()) {
            nrdyBuilder.append(((Enum<?>) item).name()).append(";");
            return;
        }

        nrdyBuilder.append(convertObjectToNrdyString(item));
    }*/
}
