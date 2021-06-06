import React, {useEffect} from 'react';
import {View, Text, NativeModules} from 'react-native';

const App = () => {
  useEffect(() => {
    NativeModules.SatisMeter.hook(
      '<userId>', //user id
      '<writeKey>', // satismeter write key
      { // extra payload
        name: 'John Doe',
        company: "Panda's Burgers",
      },
      (message) => {
        console.log(message, 'message from hook');
      },
    );
  }, []);
  return (
    <View style={{flex: 1, justifyContent: 'center', alignItems: 'center'}}>
      <Text>SatisMeter Integration</Text>
    </View>
  );
};

export default App;
