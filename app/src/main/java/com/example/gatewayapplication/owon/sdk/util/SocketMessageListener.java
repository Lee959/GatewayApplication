package owon.sdk.util;

/**
 * Socket message listener interface for receiving callbacks from the server.
 * This interface defines the method for receiving messages from devices and gateways.
 */
public interface SocketMessageListener {

    /**
     * Callback method for receiving messages from devices and gateways.
     *
     * @param commandID The command ID indicating the type of message.
     *                  This ID corresponds to the constants defined in the Constants class.
     * @param bean      The data bean containing the response data.
     *                  This bean needs to be cast to the appropriate device-specific subclass.
     */
    void getMessage(int commandID, Object bean);
}