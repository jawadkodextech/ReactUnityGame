
import React, { useState } from 'react';
import {
  Text,
  StatusBar,
  View,
  StyleSheet,
  Platform,
  TouchableOpacity,
  Image,
} from 'react-native';
import { Icons } from './assets/icons';

import { NativeModules } from 'react-native';

const { VoiceChangingModule } = NativeModules;

const App = () => {
  const [msge, setMesge] = useState('');
  // const audioTrackURL =
  //   'https://bublup-media-production.s3.amazonaws.com/14253646-e0c6-48c1-bb30-47320f7c2f82/file_uploads/dfe964da.mp3';

  const changeToAlein = () => {
    if (Platform.OS === 'android') {
      VoiceChangingModule.changeVoiceToAlien((msg) => {
        setMesge(msg);
        console.log(msg);
        // alert(msg);
      });
    } else {
      VoiceChangingModule.changeVoiceToAlien();
    }

    // 
    //   ? 
    //   : VoiceChangingModule.changeVoiceToAlien();
  };

  const ongame = (param)=>{
    if (Platform.OS === 'android') {
      VoiceChangingModule.changeVoiceToAlien(param.toString(),(msg) => {
        setMesge(msg);
        console.log(msg);
        // alert(msg);
      });
    } else {
      VoiceChangingModule.changeVoiceToAlien();
    }
  }


  const changeToChild = () => {
    Platform.OS === 'android'
      ? VoiceChangingModule.changeVoiceToChild(audioTrackURL)
      : VoiceChangingModule.changeVoiceToChild();
  };

  const changeToFast = () => {
    Platform.OS === 'android'
      ? VoiceChangingModule.speedUpVoice(audioTrackURL)
      : VoiceChangingModule.speedUpVoice();
  };

  const changeToSlow = () => {
    Platform.OS === 'android'
      ? VoiceChangingModule.slowDownVoice(audioTrackURL)
      : VoiceChangingModule.slowDownVoice();
  };

  return (
    <View style={styles.container}>
      <StatusBar barStyle="dark-content" backgroundColor={'#e4e5ea'} />
      <Text style={styles.title}>Voice Unity In React Native</Text>
      
      {/* <View style={styles.iconsContainer}>
        <TouchableOpacity onPress={() => changeToAlein()}>
          <Image
            source={Icons.alien}
            resizeMode={'contain'}
            style={styles.icon}
          />
          <Text style={{ color: "black", textAlign: 'center' }}>Goes to Unity</Text>
        </TouchableOpacity>
        <Text style={styles.title}>{msge}</Text> */}
        {/*         
        <TouchableOpacity onPress={() => changeToChild()}>
          <Image
            source={Icons.child}
            resizeMode={'contain'}
            style={styles.icon}
          />
          <Text>Child</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={() => changeToFast()}>
          <Image
            source={Icons.fast}
            resizeMode={'contain'}
            style={styles.icon}
          />
          <Text>Fast</Text>
        </TouchableOpacity>
        <TouchableOpacity onPress={() => changeToSlow()}>
          <Image
            source={Icons.slow}
            resizeMode={'contain'}
            style={styles.icon}
          />
          <Text>Slow</Text>
        </TouchableOpacity> */}
      {/* </View> */}

      <View style={{width:'100%',justifyContent:'center',alignItems:'center'}}>
      <TouchableOpacity onPress={()=>ongame(0)} style={{backgroundColor:'#7DA5FF',margin:5,height:40,width:100,borderRadius:10,alignItems:'center',justifyContent:'center'}}>
          <Text style={{color:'#fff'}}>Game 1</Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={()=>ongame(1)} style={{backgroundColor:'#CC3E44',margin:5,height:40,width:100,borderRadius:10,alignItems:'center',justifyContent:'center'}}>
          <Text style={{color:'#fff'}}>Game 2</Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={()=>ongame(2)} style={{backgroundColor:'#CC3E44',margin:5,height:40,width:100,borderRadius:10,alignItems:'center',justifyContent:'center'}}>
          <Text style={{color:'#fff'}}>Game 3</Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={()=>ongame(3)} style={{backgroundColor:'#CC3E44',margin:5,height:40,width:100,borderRadius:10,alignItems:'center',justifyContent:'center'}}>
          <Text style={{color:'#fff'}}>Game 4</Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={()=>ongame(4)} style={{backgroundColor:'#CC3E44',margin:5,height:40,width:100,borderRadius:10,alignItems:'center',justifyContent:'center'}}>
          <Text style={{color:'#fff'}}>Game 5</Text>
      </TouchableOpacity>

      <TouchableOpacity onPress={()=>ongame(5)} style={{backgroundColor:'#CC3E44',margin:5,height:40,width:100,borderRadius:10,alignItems:'center',justifyContent:'center'}}>
          <Text style={{color:'#fff'}}>Game 6</Text>
      </TouchableOpacity>
      </View>

      <Text style={styles.title}>{msge}</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    backgroundColor: '#e4e5ea',
    flex: 1,
    paddingTop: 50,
    alignItems: 'center',
  },
  title: {
    flex:1,
    fontSize: 12,
    color: '#000',
    marginVertical: 25,
  },
  iconsContainer: {
    // flexDirection: 'row',
    alignItems: 'center',
    // justifyContent: 'space-evenly',
    // width: '100%',
    paddingHorizontal: 50,
  },
  warningText: {
    color: 'red',
    fontWeight: 'bold',
    letterSpacing: 1.5,
    textAlign: 'center',
  },
  spacing: {
    marginVertical: 10,
  },
  row: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
    width: '40%',
  },
  icon: {
    height: 40,
    width: 40,
    marginBottom: 15,
  },
});

export default App;

// /**
//  * Sample React Native App
//  * https://github.com/facebook/react-native
//  *
//  * @format
//  * @flow strict-local
//  */

// import React from 'react';
// import {
//   SafeAreaView,
//   ScrollView,
//   StatusBar,
//   StyleSheet,
//   Text,
//   useColorScheme,
//   View,
// } from 'react-native';

// import {
//   Colors,
//   DebugInstructions,
//   Header,
//   LearnMoreLinks,
//   ReloadInstructions,
// } from 'react-native/Libraries/NewAppScreen';

// const Section = ({children, title}) => {
//   const isDarkMode = useColorScheme() === 'dark';
//   return (
//     <View style={styles.sectionContainer}>
//       <Text
//         style={[
//           styles.sectionTitle,
//           {
//             color: isDarkMode ? Colors.white : Colors.black,
//           },
//         ]}>
//         {title}
//       </Text>
//       <Text
//         style={[
//           styles.sectionDescription,
//           {
//             color: isDarkMode ? Colors.light : Colors.dark,
//           },
//         ]}>
//         {children}
//       </Text>
//     </View>
//   );
// };

// const App = () => {
//   const isDarkMode = useColorScheme() === 'dark';

//   const backgroundStyle = {
//     backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
//   };

//   return (
//     <SafeAreaView style={backgroundStyle}>
//       <StatusBar barStyle={isDarkMode ? 'light-content' : 'dark-content'} />
//       <ScrollView
//         contentInsetAdjustmentBehavior="automatic"
//         style={backgroundStyle}>
//         <Header />
//         <View
//           style={{
//             backgroundColor: isDarkMode ? Colors.black : Colors.white,
//           }}>
//           <Section title="Step One">
//             Edit <Text style={styles.highlight}>App.js</Text> to change this
//             screen and then come back to see your edits.
//           </Section>
//           <Section title="See Your Changes">
//             <ReloadInstructions />
//           </Section>
//           <Section title="Debug">
//             <DebugInstructions />
//           </Section>
//           <Section title="Learn More">
//             Read the docs to discover what to do next:
//           </Section>
//           <LearnMoreLinks />
//         </View>
//       </ScrollView>
//     </SafeAreaView>
//   );
// };

// const styles = StyleSheet.create({
//   sectionContainer: {
//     marginTop: 32,
//     paddingHorizontal: 24,
//   },
//   sectionTitle: {
//     fontSize: 24,
//     fontWeight: '600',
//   },
//   sectionDescription: {
//     marginTop: 8,
//     fontSize: 18,
//     fontWeight: '400',
//   },
//   highlight: {
//     fontWeight: '700',
//   },
// });

// export default App;
