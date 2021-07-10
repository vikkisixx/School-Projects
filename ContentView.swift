
// main view with views (sliders, buttons, text)
// responsible for what the main screen on the app looks like

import SwiftUI

struct ContentView: View {                                              // View class: displays UI. Not game logic
    
    @State private var alertIsVisible = false                           // initial states
    @State private var degrees = 45.0                                   // angle input, must be String for TextField
    @State private var sliderValue = 25.0                               // refresh body if state changes
    @State private var game: Game = Game()                              // new instance of Game
    
    var body: some View {
        ZStack {
            BackgroundView(game: $game)                                 // background
            VStack {                                                    // vertical stack
                EmptyView()
                InstructionsView(game: $game)                           // something about binding
                if alertIsVisible {
                    PointsView(alertIsVisible: $alertIsVisible, degrees: $degrees, sliderValue: $sliderValue, game: $game)
                        .transition(.scale)                             // animation for popup
                } else {
                    AngleView(degrees: $degrees)
                        .padding(.bottom, 80)
                        .transition(.scale)
                    FireButton(alertIsVisible: $alertIsVisible, sliderValue: $sliderValue, degrees: $degrees, game: $game)
                        .padding(.bottom, 25)
                        .transition(.scale)                             // animation for button
                }
            }
            
            if !alertIsVisible {
            SliderView(sliderValue: $sliderValue)
                .padding(.top, 170)
                .transition(.scale)                                     // animation for slider
            }
        }
    }
}

struct InstructionsView: View {
    @Binding var game: Game                                             // parameter
    
    var body: some View{
        VStack {                                                        // only returns 1 view so use VStack
            HitText(text: "\nhit target within")
                .padding(.top, 170)
            let tLocation = game.target
            BoldNumberText(text: String(tLocation - 2) + " m - " + String(tLocation + 2) + " m")
        }
        .background(Image("Bullseye").opacity(0.05), alignment: .top)
    }
}

struct AngleView: View {
    @Binding var degrees: Double
    
    var body: some View {
         HStack {
            SliderText(text: "0°")
         Slider(value: $degrees, in: 1.0...90.0)
            SliderText(text: "90°")
         }
         .padding()
    }
}

struct SliderView: View {
    @Binding var sliderValue: Double                        // parameter
    
    var body: some View {
        HStack {                                            // horizontal stack
            Image("archer")
                .resizable()
                .scaledToFit()
                .frame(width: 30, height: 30)
            SliderText(text: "  1\nm/s")
            Slider(value: $sliderValue, in: 1.0...50.0)
            SliderText(text: " 50\nm/s")
            Image("target")
                .resizable()
                .scaledToFit()
                .frame(width: 30, height: 30)
        }
        .padding()                                          // adds more space between hstacks
    }
}

struct FireButton: View {
    @Binding var alertIsVisible: Bool                       // parameters
    @Binding var sliderValue: Double
    @Binding var degrees: Double
    @Binding var game: Game
    
    var body: some View {
        Button(action: {                                    // Button(action: {code}) {visuals}
            withAnimation {
            alertIsVisible = true                           // change state, refresh body
            }
        }) {
            Text("fire")                                    // button title
                .bold()
                .font(.title3)
        }
        .padding(20.0)                                      // adds extra transparent space around edges
        .background(
            ZStack {
                Color("ButtonColor")
                LinearGradient(gradient: Gradient(colors: [Color.white.opacity(0.3), Color.clear]), startPoint: .top, endPoint: .bottom)
            })
        .foregroundColor(Color.white)
        .cornerRadius(Constants.General.RoundRectCornerRadius)                      // rounds square background
        .overlay(                                                                   // like ZStack
            RoundedRectangle(cornerRadius: Constants.General.RoundRectCornerRadius) // rounded border
                .strokeBorder(Color.black, lineWidth: Constants.General.strokeWidth)
        )
        .alert(isPresented: $alertIsVisible, content: {                             // popup if
            let points = game.points(sliderValue: Int(sliderValue.rounded()), degrees: Int( degrees ) )
            return Alert(title: Text("archery results"),
                  message: Text("arrow hit at: \(Int(sliderValue.rounded())) meters." +
                                "\nscore: \(points)/100 points"),
                  dismissButton: .default(Text("try again")) {
                    game.startNewRound(points: points)
                  }
            )
        })
    }
}

struct ContentView_Previews: PreviewProvider {                  // code for previews on the right
    static var previews: some View {
        ContentView()
            .previewDevice("iPhone 12 mini")                                           // portait orientation
    }
}
