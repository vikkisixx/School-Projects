


import SwiftUI

struct PointsView: View {
    
    @Binding var alertIsVisible: Bool                       // parameters
    @Binding var degrees: Double
    @Binding var sliderValue: Double
    @Binding var game: Game
    
    var body: some View {
        
        let roundedValue = Int(sliderValue.rounded())
        let angle = Int(degrees.rounded())
        let points = game.points(sliderValue: roundedValue, degrees: angle)
        
        VStack{
            HitText(text: "velocity: \(Int(sliderValue)) m/s" )
            HitText(text: "angle: \(angle)°" )
            HitText(text: "landed at: ")
            BoldNumberText(text: String( Int( game.landed(sliderValue: roundedValue, degrees: angle).rounded() ) ) + " m")
            BodyText(text: "score: \(points)")
            Button(action: { withAnimation {
                alertIsVisible = false
            }
            game.startNewRound(points: Int(points))         // might cause issues with doubles & ints
            }) {
                ButtonText(text: "new round")
            }
        }
            .padding()
        .frame(maxWidth: 300)
            .cornerRadius(Constants.General.RoundRectCornerRadius)
            .shadow(radius: 10, x: 5, y: 5)
            .transition(.scale)
    }
}

struct PointsView_Previews: PreviewProvider {
    
    static private var alertIsVisible = Binding.constant(false)
    static private var degrees = Binding.constant(45.0)
    static private var sliderValue = Binding.constant(50.0)
    static private var game = Binding.constant(Game()) 
    
    static var previews: some View {
       
        PointsView(alertIsVisible: alertIsVisible, degrees: degrees, sliderValue: sliderValue, game: game)  // portait
    }
}
