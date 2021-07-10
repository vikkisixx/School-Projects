
// collection of template Round Views


import SwiftUI

struct RoundedImageViewStroked: View {              // stroke = border
    var systemName: String
    
    var body: some View {
        Image(systemName: systemName)
            .font(.title)                           // size of icon
            .foregroundColor(Color("TextColor"))    // icon will be black
            .frame(width: Constants.General.roundedViewLength, height: Constants.General.roundedViewLength) //  size
            .overlay(                               // like ZStack
                Circle()                            // adds circle over icon
                .strokeBorder(Color("ButtonStrokeColor"), lineWidth: Constants.General.strokeWidth)         // border
            )
    }
}

struct RoundedImageViewFilled: View {                                           // fill = colored in
    var systemName: String
    
    var body: some View {
        Image(systemName: systemName)
            .font(.title)                                                       // size of icon
            .foregroundColor(Color("ButtonFilledTextColor"))                    // icon will be black (in dark mode?)
            .frame(width: Constants.General.roundedViewLength, height: Constants.General.roundedViewLength)     // size of frame
            .background(
                Circle()
                .fill(Color("ButtonFilledBackgroundColor"))                    // fill will be black
            )
    }
}

struct RoundRectTextView: View {
    var text: String                                                            // parameter
    
    var body: some View {
        Text(text)
            .kerning(-0.2)
            .bold()
            .font(.title3)
            .frame(width: Constants.General.RoundRectViewWidth, height: Constants.General.RoundRectViewHeight)
            .foregroundColor((Color("TextColor")))
            .overlay(
                RoundedRectangle(cornerRadius: Constants.General.RoundRectCornerRadius)                        // places rect ontop "100"
                    .stroke(lineWidth:Constants.General.strokeWidth)        // makes rect border skinny
                    .foregroundColor(Color("ButtonStrokeColor"))
            )
    }
}

struct RoundedTextView: View {
    let text: String
    
    var body: some View{
        Text(text)
            .font(.title)                           // size of icon
            .foregroundColor(Color("TextColor"))    // icon will be black
            .frame(width: Constants.General.roundedViewLength, height: Constants.General.roundedViewLength) //  size
            .overlay(                               // like ZStack
                Circle()                            // adds circle over icon
                    .strokeBorder(Color.red, lineWidth: Constants.General.strokeWidth) )
    }
}

struct PreviewView: View {
    
    var body: some View {
        VStack(spacing: 10) {                                               // adds 10px space between vertical elements
            RoundedImageViewStroked(systemName: "arrow.counterclockwise")
            RoundedImageViewFilled(systemName: "list.dash")
            RoundRectTextView(text: "100")
            RoundedTextView(text: "1")
        }
    }
}

struct RoundViews_Previews: PreviewProvider {
    
    static var previews: some View {
        PreviewView()
        PreviewView()
            .preferredColorScheme(.dark)
    }
}
