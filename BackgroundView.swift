
// collection of Background views

import SwiftUI

struct BackgroundView: View {
    @Binding var game: Game
    
    var body: some View {
        VStack {
            TopView(game: $game)
            Spacer()                                                // spaces out vertically
            BottomView(game: $game)
        }
        .padding()
    }
}

struct TopView: View {
    @Binding var game: Game
    @State private var leaderboardIsShowing = false
    
    var body: some View {
        HStack {
            Button(action: {
                game.restart()
            }) {
                RoundedImageViewStroked(systemName: "arrow.counterclockwise")
            }
            Spacer()                                                // spaces horizontally as much as possible
            Button(action: {
                leaderboardIsShowing = true
            }) {
                RoundedImageViewFilled(systemName: "list.dash")
            }
            .sheet(isPresented: $leaderboardIsShowing,
                   onDismiss: {},
                   content: {LeaderboardView(leaderboardIsShowing: $leaderboardIsShowing, game: $game) }
            )
        }
        .background(Image("BackgroundTree"))
    }
}

struct NumberView: View {
    var title: String
    var text: String
    
    var body: some View{
        VStack(spacing: 5){
            LabelText(text: title.uppercased())
            RoundRectTextView(text: text)
        }
    }
}

struct BottomView: View {
    @Binding var game: Game
    
    var body: some View {
        HStack {
            NumberView(title: "Score", text: String(game.score))
            Spacer()                                                // spaces out as much as possible
            NumberView(title: "Round", text: String(game.round))
        }
    }
}

struct BackgroundView_Previews: PreviewProvider {
    static var previews: some View {
        BackgroundView(game: .constant(Game()))
    }
}
